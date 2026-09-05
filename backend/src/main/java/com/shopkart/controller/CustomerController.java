package com.shopkart.controller;

import com.shopkart.dto.CustomerRequest;
import com.shopkart.dto.CustomerResponse;
import com.shopkart.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse
                = customerService.createCustomer(customerRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerResponse);
    }

}
