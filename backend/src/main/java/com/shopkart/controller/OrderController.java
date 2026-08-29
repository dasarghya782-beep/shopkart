package com.shopkart.controller;

import com.shopkart.dto.OrderRequest;
import com.shopkart.dto.OrderResponse;
import com.shopkart.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(
            @RequestBody OrderRequest request){

        OrderResponse response = orderService.placeOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
