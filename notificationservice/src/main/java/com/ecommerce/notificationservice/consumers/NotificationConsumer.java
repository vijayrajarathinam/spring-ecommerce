package com.ecommerce.notificationservice.consumers;


import com.ecommerce.notificationservice.consumers.entities.OrderConfirmation;
import com.ecommerce.notificationservice.consumers.entities.PaymentConfirmation;
import com.ecommerce.notificationservice.entities.Notification;
import com.ecommerce.notificationservice.entities.NotificationType;
import com.ecommerce.notificationservice.repositories.NotificationRepository;
import com.ecommerce.notificationservice.services.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

//    @KafkaListener(topics = "PAYMENT_TOPIC")
    public void consumePaymentSuccess(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(String.format("Consuming message from payment topic :: %s", paymentConfirmation));
        notificationRepository.save(Notification.builder()
                .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build()
        );

        //send email
        var customerName = paymentConfirmation.customerFirstname()+" "+paymentConfirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName, paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }

//    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccess(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("Consuming message from order topic :: %s", orderConfirmation));
        notificationRepository.save(Notification.builder()
                .notificationType(NotificationType.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build()
        );

        //send email
        var customerName = orderConfirmation.customer().firstname() +" "+ orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName, orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}
