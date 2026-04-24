package com.commerceapp.order.entity;

import com.commerceapp.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 엔티티 생성 후 합치고 주석 처리 제거
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id", nullable = false)
//    private Customer customer

    @Column(nullable = false)
    private int quantity;

    @Column(name = "total_price", nullable = false)
    private int totalPrice = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status = OrderStatus.READY;

    // 엔티티 생성 후 합치고 주석 처리 제거
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "admin_id")
//    private Admin admin

    @Column(name = "is_canceled", nullable = false)
    private boolean isCanceled;

    private enum OrderStatus {
        READY,
        SHIPPING,
        COMPLETED
    }
}
