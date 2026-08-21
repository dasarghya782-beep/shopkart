package com.shopkart.service;

import com.shopkart.dto.CustomerRequest;
import com.shopkart.dto.CustomerResponse;
import com.shopkart.exception.CustomerNotFoundException;
import com.shopkart.mapper.CustomerMapper;
import com.shopkart.model.Customer;
import com.shopkart.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository,CustomerMapper customerMapper){
        this.customerRepository=customerRepository;
        this.customerMapper=customerMapper;
    }

    public List<CustomerResponse> getAllCustomers(){
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerResponse)
                .toList();
    }

    public CustomerResponse getCustomerById(Long customerId){

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException(customerId));

        return customerMapper.customerResponse(customer);
    }

    public CustomerResponse createCustomer(CustomerRequest customerRequest){

        Customer customer = customerMapper.toEntity(customerRequest);

        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.customerResponse(savedCustomer);
    }

}
