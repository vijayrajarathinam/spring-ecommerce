package com.ecommerce.orderservice.producer.entities;

import com.ecommerce.orderservice.entities.PaymentMethod;
import com.ecommerce.orderservice.responses.CustomerResponse;
import com.ecommerce.orderservice.responses.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
