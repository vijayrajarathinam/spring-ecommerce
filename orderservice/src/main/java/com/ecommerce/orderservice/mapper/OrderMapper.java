package com.ecommerce.orderservice.mapper;


import com.ecommerce.orderservice.entities.Order;
import com.ecommerce.orderservice.entities.OrderItem;
import com.ecommerce.orderservice.requests.OrderItemRequest;
import com.ecommerce.orderservice.requests.OrderRequest;

import com.ecommerce.orderservice.responses.OrderItemResponse;
import com.ecommerce.orderservice.responses.OrderResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderMapper {


    public Order fromOrderRequest(OrderRequest orderRequest) {
        return Order.builder().id(orderRequest.id())
                .customerId(orderRequest.customerId())
                .reference(orderRequest.reference())
                .totalAmount(orderRequest.totalAmount())
                .paymentMethod(orderRequest.paymentMethod())
                .build();
    }

    public OrderItem fromOrderItemRequest(OrderItemRequest orderItemRequest) {
        return OrderItem.builder().id(orderItemRequest.id())
                .order(
                   Order.builder().id(orderItemRequest.orderId()).build()
                )
                .productId(orderItemRequest.productId())
                .quantity(orderItemRequest.quantity()).build();
    }

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }

    public OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        return new OrderItemResponse(orderItem.getId(),orderItem.getQuantity());
    }
}
