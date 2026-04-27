package com.commerceapp.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "orderitem")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order id", nullable = false)
    private Long orderId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_name", nullable = false, length = 255)
    private String productName;

    @Column(nullable = false)
    private int price = 0;

    @Column(nullable = false)
    private int quantity = 0;

    @Column(name = "total_price", nullable = false)
    private int totalPrice = 0;

    public static OrderItemEntity create(
            Long orderId,
            Long productId,
            String productName,
            int price,
            int quantity
    ) {
        OrderItemEntity item = new OrderItemEntity();
        item.orderId = orderId;
        item.productId = productId;
        item.productName = productName;
        item.price = price;
        item.quantity = quantity;
        item.totalPrice = price * quantity;
        return item;
    }

}
