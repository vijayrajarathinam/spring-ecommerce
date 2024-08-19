package com.ecommerce.notificationservice.consumers.entities;

import java.math.BigDecimal;

public record Product(
        Integer productId,
        String name,
        String description,
        double quantity,
        BigDecimal price
//        private Category category;
) {
}
