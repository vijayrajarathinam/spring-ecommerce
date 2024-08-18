package com.ecommerce.orderservice.service;


import com.ecommerce.orderservice.mapper.OrderMapper;
import com.ecommerce.orderservice.repositories.OrderItemRepository;
import com.ecommerce.orderservice.requests.OrderItemRequest;
import com.ecommerce.orderservice.responses.OrderItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;

    public Integer saveOrderItem(OrderItemRequest orderItemRequest) {
        var orderItem = orderMapper.fromOrderItemRequest(orderItemRequest);
        return orderItem.getId();
    }

    public List<OrderItemResponse> findAllByOrderId(Integer orderId) {
        return orderItemRepository.findAllByOrderId(orderId).stream()
                .map(orderMapper::toOrderItemResponse)
                .collect(Collectors.toList());
    }
}
