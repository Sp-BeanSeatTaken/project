package com.commerceapp.common.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders/{orderId}/items")
public class OrderItemController {

    // 주문 상품 추가
    @PostMapping
    public void createItem(@PathVariable Long orderId) {

    }

    // 주문 상품 리스트 조회
    @GetMapping
    public void getItems(@PathVariable Long orderId) {

    }

    // 주문 상품 삭제
    @PatchMapping
    public void updateItem(@PathVariable Long orderId, @PathVariable Long itemId) {

    }
}
