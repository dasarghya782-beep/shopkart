package com.shopkart.dto;

public record CustomerRequest(
        String name,
        String email,
        String city
) {
}
