package com.commerceapp.product.dto;

import com.commerceapp.product.entity.Product;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductResponse {
    // 속성

    private Long id;
    private String name;
    private String category;
    private int price;
    private int stock;
    private String state;
    private LocalDateTime createdAt;
    private String createdByName;

    // 생성자

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.state = product.getState();
        this.createdAt = product.getCreatedAt();
        this.createdByName = product.getCreatedByName();

    }

}
