package com.commerceapp.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OrderItemRequest {
    @NotNull(message = "상품 번호는 필수 입력입니다.")
    private Long productId;

    @NotBlank(message = "수량을 입력해주세요.")
    private int quantity;
}
