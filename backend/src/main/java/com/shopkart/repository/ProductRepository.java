package com.shopkart.repository;

import com.shopkart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("""
            SELECT DISTINCT p
            FROM Product p
            LEFT JOIN p.tags tag
            WHERE (
                :query IS NULL
                OR :query = ''
                OR LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(p.category) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(tag) LIKE LOWER(CONCAT('%', :query, '%'))
            )
            AND (
                :maxPrice IS NULL
                OR p.price <= :maxPrice
            )
            ORDER BY p.rating DESC
            """)
    List<Product> search(
            @Param("query") String query,
            @Param("maxPrice") BigDecimal maxPrice
    );
}
