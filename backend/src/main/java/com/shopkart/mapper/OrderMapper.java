package com.shopkart.mapper;

import com.shopkart.dto.CustomerResponse;
import com.shopkart.dto.OrderItemResponse;
import com.shopkart.dto.OrderResponse;
import com.shopkart.model.Order;
import com.shopkart.model.OrderItem;
import com.shopkart.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderMapper {
    private final CustomerMapper customerMapper;

    public OrderMapper(CustomerMapper customerMapper){
        this.customerMapper=customerMapper;
    }

    public OrderResponse toResponse(Order order){
        CustomerResponse customerResponse =
                customerMapper.customerResponse(order.getCustomer());

        List<OrderItemResponse> itemResponses = order.getItems()
                .stream()
                .map(this::toItemResponse)
                .toList();

        return new OrderResponse(
                order.getId(),
                customerResponse,
                itemResponses,
                order.getTotal(),
                order.getStatus()
        );
    }

    private OrderItemResponse toItemResponse(OrderItem orderItem){
        Product product = orderItem.getProduct();

        BigDecimal subTotal = product.getPrice()
                .multiply(BigDecimal.valueOf(orderItem.getQuantity()));

        return new OrderItemResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                orderItem.getQuantity(),
                subTotal
        );
    }
}
