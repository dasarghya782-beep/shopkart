package com.shopkart.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequest(
        String name,
        String category,
        String brand,
        BigDecimal price,
        int stock,
        String description,
        List<String> tags
) {
}
