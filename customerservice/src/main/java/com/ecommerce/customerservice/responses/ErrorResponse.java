package com.ecommerce.customerservice.responses;


import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {}
