package com.shopkart.dto;

import com.shopkart.model.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(
        Long id,
        CustomerResponse customer,
        List<OrderItemResponse> items,
        BigDecimal total,
        OrderStatus status
) {
}
