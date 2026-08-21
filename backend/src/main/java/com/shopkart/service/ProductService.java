package com.shopkart.service;

import com.shopkart.dto.ProductResponse;
import com.shopkart.mapper.ProductMapper;
import com.shopkart.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository,ProductMapper productMapper){
        this.productRepository=productRepository;
        this.productMapper=productMapper;
    }

    public List<ProductResponse> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(productMapper::productResponse)
                .toList();
    }

}
