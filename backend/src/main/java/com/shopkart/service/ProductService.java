package com.shopkart.service;

import com.shopkart.dto.ProductRequest;
import com.shopkart.dto.ProductResponse;
import com.shopkart.exception.ProductNotFoundException;
import com.shopkart.mapper.ProductMapper;
import com.shopkart.model.Product;
import com.shopkart.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public ProductResponse getProductById(Long id){
        Product product= productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(id));

        return productMapper.productResponse(product);
    }

    public List<ProductResponse> searchProducts(String query, BigDecimal maxPrice){
        return productRepository.search(query, maxPrice)
                .stream()
                .map(productMapper::productResponse)
                .toList();
    }

    public ProductResponse createProduct(ProductRequest request){
        Product product = productMapper.toEntity(request);
        Product savedProduct = productRepository.save(product);
        return productMapper.productResponse(savedProduct);
    }

}
