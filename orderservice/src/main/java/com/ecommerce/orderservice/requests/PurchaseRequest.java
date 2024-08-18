package com.ecommerce.orderservice.requests;

import jakarta.validation.constraints.NotNull;

public record PurchaseRequest(

        @NotNull(message = "product is mandatory")
        Integer productId,
        @NotNull(message = "quantity is mandatory")
        double quantity
) {
}
