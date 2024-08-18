package com.ecommerce.paymentservice.services;


import com.ecommerce.paymentservice.entities.Payment;
import com.ecommerce.paymentservice.mappers.PaymentMapper;
import com.ecommerce.paymentservice.producer.NotificationProducer;
import com.ecommerce.paymentservice.producer.entities.PaymentNotificationRequest;
import com.ecommerce.paymentservice.repositories.PaymentRepository;
import com.ecommerce.paymentservice.requests.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest paymentRequest) {
        var payment = paymentRepository.save(
                paymentMapper.fromPaymentRequest(paymentRequest)
        );

        notificationProducer.sendNotification(
            new PaymentNotificationRequest(
                paymentRequest.orderReference(),
                paymentRequest.amount(),
                paymentRequest.paymentMethod(),
                paymentRequest.customer().firstname(),
                paymentRequest.customer().lastname(),
                paymentRequest.customer().email()
            )
        );

        return payment.getId();
    }
}
