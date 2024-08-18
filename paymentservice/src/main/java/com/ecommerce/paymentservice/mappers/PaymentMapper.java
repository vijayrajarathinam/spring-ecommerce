package com.ecommerce.paymentservice.mappers;


import com.ecommerce.paymentservice.entities.Payment;
import com.ecommerce.paymentservice.requests.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment fromPaymentRequest(PaymentRequest paymentRequest) {
        return Payment.builder()
                .id(paymentRequest.id())
                .orderId(paymentRequest.orderId())
                .paymentMethod(paymentRequest.paymentMethod())
                .amount(paymentRequest.amount())
                .build();
    }
}
