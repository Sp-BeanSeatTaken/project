package com.commerceapp.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateRequest {
    @NotNull(message = "고객 번호는 필수 입력입니다.")
    private Long customerId;

    private List<OrderItemRequest> itemList;
}
