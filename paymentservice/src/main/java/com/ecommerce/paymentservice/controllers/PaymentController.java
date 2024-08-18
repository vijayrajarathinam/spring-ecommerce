package com.ecommerce.paymentservice.controllers;


import com.ecommerce.paymentservice.requests.PaymentRequest;
import com.ecommerce.paymentservice.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Integer> createPayments(
            @RequestBody @Valid PaymentRequest paymentRequest
    ){
        return ResponseEntity.ok(paymentService.createPayment(paymentRequest));

    }

}
