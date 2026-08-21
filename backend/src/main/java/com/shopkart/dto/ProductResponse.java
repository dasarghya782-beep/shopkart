package com.shopkart.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(
        Long id,
        String name,
        String category,
        String brand,
        BigDecimal price,
        double rating,
        int stock,
        String description,
        List<String> tags
) {
}
