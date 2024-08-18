package com.ecommerce.orderservice.responses;


public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) { }
