package com.ecommerce.orderservice.controllers;


import com.ecommerce.orderservice.entities.OrderItem;
import com.ecommerce.orderservice.responses.OrderItemResponse;
import com.ecommerce.orderservice.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order_items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderItemResponse>> findByOrderId(
       @PathVariable Integer orderId
    ){
       return ResponseEntity.ok(orderItemService.findAllByOrderId(orderId));
    }
}
