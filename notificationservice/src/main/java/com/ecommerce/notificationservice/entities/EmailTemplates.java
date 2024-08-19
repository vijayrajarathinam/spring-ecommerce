package com.ecommerce.notificationservice.entities;

import lombok.Getter;

public enum EmailTemplates {
    ORDER_CONFIRMATION("order-confirmation.html","Order successfully processed"),
    PAYMENT_CONFIRMATION("payment-confirmation.html","Payment successfully processed");

    @Getter private final String template;
    @Getter private final String subject;
    EmailTemplates(String template, String subject){
        this.template = template;
        this.subject = subject;
    }
}
