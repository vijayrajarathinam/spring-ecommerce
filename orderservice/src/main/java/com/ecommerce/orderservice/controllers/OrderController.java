package com.ecommerce.orderservice.controllers;


import com.ecommerce.orderservice.requests.OrderRequest;
import com.ecommerce.orderservice.responses.OrderResponse;
import com.ecommerce.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Integer> createOrder(
            @RequestBody @Valid OrderRequest orderRequest
    ){
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrder(){
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Integer orderId){
        return ResponseEntity.ok(orderService.findById(orderId));
    }



}
