package com.shopkart.mapper;

import com.shopkart.dto.CustomerRequest;
import com.shopkart.dto.CustomerResponse;
import com.shopkart.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerResponse customerResponse(Customer customer){
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getCity()
        );
    }
    public Customer toEntity(CustomerRequest customerRequest){
        return new Customer(
                customerRequest.name(),
                customerRequest.email(),
                customerRequest.city()
        );
    }
}
