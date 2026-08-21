package com.shopkart.mapper;

import com.shopkart.dto.ProductResponse;
import com.shopkart.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse productResponse(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getBrand(),
                product.getPrice(),
                product.getRating(),
                product.getStock(),
                product.getDescription(),
                product.getTags()
        );
    }
}
