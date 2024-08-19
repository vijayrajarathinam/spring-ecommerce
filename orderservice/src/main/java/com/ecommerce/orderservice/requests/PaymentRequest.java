package com.ecommerce.orderservice.requests;

import com.ecommerce.orderservice.entities.PaymentMethod;
import com.ecommerce.orderservice.responses.CustomerResponse;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
