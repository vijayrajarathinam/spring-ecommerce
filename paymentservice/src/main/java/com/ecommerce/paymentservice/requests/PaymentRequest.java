package com.ecommerce.paymentservice.requests;

import com.ecommerce.paymentservice.entities.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) { }
