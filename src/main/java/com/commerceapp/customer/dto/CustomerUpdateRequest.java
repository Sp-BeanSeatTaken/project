package com.commerceapp.customer.dto;

import lombok.Getter;

/**
 * 고객 정보 수정 요청 DTO
 * 고객 정보 수정 API 요청 시 사용됩니다.
 * 수정 가능한 필드: 이름, 이메일, 전화번호
 */
@Getter
public class CustomerUpdateRequest {
    private String name;
    private String email;
    private String phoneNumber;
}
