package com.shopkart.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    private String name;
    private String category;
    private String brand;
    @Column(precision = 10,scale = 2)
    private BigDecimal price;
    private double rating;
    private int stock;
    private String description;

    @ElementCollection
    @CollectionTable(
            name = "product_tags",
            joinColumns = @JoinColumn(name = "product_id")
    )
    @Column(name = "tag")
    private List<String> tags = new ArrayList<>();

    public Product(String name, String category, String brand, BigDecimal price, int stock, String description, List<String> tags) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.tags = tags;
    }
    @Override
    public int hashCode(){
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        if (o==null || getClass() != o.getClass()){
            return false;
        }

        Product product = (Product) o;
        return id!=null && id.equals(product.id);
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                '}';
    }
}