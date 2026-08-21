package com.shopkart.dto;

public record CustomerResponse(
        Long id,
        String name,
        String email,
        String city
) {
}
