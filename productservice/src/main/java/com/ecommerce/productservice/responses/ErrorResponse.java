package com.ecommerce.productservice.responses;


import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {}
