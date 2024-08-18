package com.ecommerce.orderservice.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemRequest(
         Integer id,
         @NotNull(message = "Order item info is mandatory")
         Integer orderId,
         @NotNull(message = "Product is mandatory")
         Integer productId,
         @Positive(message = "Quantity is mandatory")
         double quantity
) { }
