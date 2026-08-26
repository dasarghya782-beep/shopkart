package com.shopkart.dto;

import java.util.List;

public record OrderRequest(
        Long customerId,
        List<OrderItemRequest> items
) {
}
