package com.commerceapp.product.entity;

import com.commerceapp.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    //속성

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  상품 이름
    @Column(nullable = false, length = 255)
    private String name;

    // 카테고리
    @Column(nullable = false, length = 30)
    private String category;

    // 가격
    @Column(nullable = false)
    private int price;

    // 재고
    @Column(nullable = false)
    private int stock;

    @Column(nullable = false, length = 30)
    private ProductStatus state = ProductStatus.ON_SALE;

    // 등록일
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // 등록 관리자 이름
    @Column(nullable = false, length = 20)
    private String createdByName;

    // 등록 관리자 이메일
    @Column(nullable = false, length = 255)
    private String createdByEmail;
    private ProductStatus state = ProductStatus.ON_SALE;

    //생성자
    public Product(String name, String category, int price, int stock, String state, String createdByName, String createdByEmail) {
//    // 등록 관리자 이름
//    @Column(nullable = false, length = 20)
//    private String createdByName;
//
//    // 등록 관리자 이메일
//    @Column(nullable = false, length = 255)
//    private String createdByEmail;

    public Product(String name, String category, int price, int stock) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
//        this.createdByName = createdByName;
//        this.createdByEmail = createdByEmail;
    }

    public void decreateStock(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");
        }

        if (this.state == ProductStatus.DISCONTINUED) {
            throw new IllegalArgumentException("단종 상품은 주문할 수 없습니다.");
        }

        if (this.stock == 0) {
            this.state = ProductStatus.SOLD_OUT;

            throw new IllegalArgumentException("품절 상품은 주문할 수 없습니다.");
        }




    //기능

    // 상품 등록 후 자동으로 처리
    @PrePersist
    public void autoCreate() {
        this.createdAt = LocalDateTime.now();

        // 기본값 "판매중"
        if (this.state == null) {
            this.state = "ON SALE";
        }
    public static Product create(
            String name,
            String category,
            int price,
            int stock,
            ProductStatus state
    ) {
        Product product = new Product();
        product.name = name;
        product.category = category;
        product.price = price;
        product.stock = stock;
        product.state = state;
        return product;
    }

    // 상품 수정
    public void update(String name, String category, int price, int stock) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public void decreateStock(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");
        }

        if (this.state == ProductStatus.DISCONTINUED) {
            throw new IllegalArgumentException("단종 상품은 주문할 수 없습니다.");
        }

        if (this.state == ProductStatus.SOLD_OUT) {
            throw new IllegalArgumentException("품절 상품은 주문할 수 없습니다.");
        }

        if (this.stock < quantity) {
            throw new IllegalArgumentException("상품의 재고가 부족합니다.");
        }

        this.stock -= quantity;

        // 재고 0 되면 자동 품절
        if (this.stock == 0) {
            this.state = ProductStatus.SOLD_OUT;
        }
    }

    public void increaseStock(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");
        }

        this.stock += quantity;
        // 단종이 아닐경우 변경
        if (!this.state.equals("DISCONTINUED")) {
            if (this.stock <= 0) {
                this.state = "SOLD_OUT";
            } else {this.state = "ON_SALE";
            }
        }
    }

    // 상태 변경
    public void changeState(String state) {
        this.state = state;
    }

}
