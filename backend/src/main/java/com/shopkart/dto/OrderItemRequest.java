package com.shopkart.dto;

public record OrderItemRequest(
        Long productId,
        int quantity
) {
}
