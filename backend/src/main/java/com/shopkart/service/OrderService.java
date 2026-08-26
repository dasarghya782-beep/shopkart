package com.shopkart.service;

import com.shopkart.dto.OrderItemRequest;
import com.shopkart.dto.OrderRequest;
import com.shopkart.dto.OrderResponse;
import com.shopkart.exception.CustomerNotFoundException;
import com.shopkart.exception.ProductNotFoundException;
import com.shopkart.mapper.OrderMapper;
import com.shopkart.model.Customer;
import com.shopkart.model.Order;
import com.shopkart.model.OrderItem;
import com.shopkart.model.Product;
import com.shopkart.repository.CustomerRepository;
import com.shopkart.repository.OrderRepository;
import com.shopkart.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class OrderService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(CustomerRepository customerRepository, ProductRepository productRepository, OrderRepository orderRepository, OrderMapper orderMapper) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest){

        Customer customer = customerRepository.findById(orderRequest.customerId())
                .orElseThrow(() -> new CustomerNotFoundException(orderRequest.customerId()));

        Order order = new Order(customer);

        BigDecimal total = BigDecimal.ZERO;

        for(OrderItemRequest itemRequest : orderRequest.items()){

            if(itemRequest.quantity()<=0){
                throw new IllegalArgumentException("Quantity must be greater then zero.");
            }

            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() ->
                            new ProductNotFoundException(itemRequest.productId()));

            product.decreaseStock(itemRequest.quantity());

            BigDecimal subtotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(itemRequest.quantity()));

            total=total.add(subtotal);

            OrderItem orderItem = new OrderItem(product, itemRequest.quantity());

            order.addItem(orderItem);
        }
        order.setTotal(total);

        Order savedOrder = orderRepository.save(order);

        return orderMapper.toResponse(savedOrder);
    }
}
