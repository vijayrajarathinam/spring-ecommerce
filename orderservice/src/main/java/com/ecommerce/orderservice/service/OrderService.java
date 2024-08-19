package com.ecommerce.orderservice.service;


import com.ecommerce.orderservice.exception.BusinessException;
import com.ecommerce.orderservice.mapper.OrderMapper;
import com.ecommerce.orderservice.producer.OrderProducer;
import com.ecommerce.orderservice.producer.entities.OrderConfirmation;
import com.ecommerce.orderservice.repositories.OrderRepository;
import com.ecommerce.orderservice.requests.OrderItemRequest;
import com.ecommerce.orderservice.requests.OrderRequest;
import com.ecommerce.orderservice.requests.PaymentRequest;
import com.ecommerce.orderservice.requests.PurchaseRequest;
import com.ecommerce.orderservice.responses.OrderResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderMapper orderMapper;
    private final OrderItemService orderItemService;
    private final OrderProducer orderProducer;
    private final PaymentService paymentService;

    // step 1: check for the customer -> OpenFeign
    // step 2: purchase the product -> RestTemplate
    // step 3: persist the order [in progress] -> Hibernate
    // step 4: initiate payment information for this order -> Kafka
    // step 5: send the order confirmation as notification -> Kafka
    public Integer createOrder(OrderRequest orderRequest) {
        var customer = customerService
                .getCustomer(orderRequest.customerId())
                .orElseThrow(
                  () -> new BusinessException("Cannot create order :: Customer not found with id :: "+orderRequest.customerId()
                ));

        var purchasedProduct = productService.purchaseProducts(orderRequest.products());
        var order = orderRepository.save(orderMapper.fromOrderRequest(orderRequest));

        for (PurchaseRequest purchaseRequest: orderRequest.products()) {
            orderItemService.saveOrderItem(new OrderItemRequest(
                    null,
                    order.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()
            ));
        }

        //start payment
        var paymentRequest = new PaymentRequest(
                orderRequest.totalAmount(),
                orderRequest.paymentMethod(),
                order.getId(), order.getReference(), customer
        );
        paymentService.requestOrderPayment(paymentRequest);

        //order confirmation
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                customer, purchasedProduct
        ));
        return order.getId();
    }

    public List<OrderResponse> getAllOrder() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::toOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException("No order found with the Id"));
    }
}
