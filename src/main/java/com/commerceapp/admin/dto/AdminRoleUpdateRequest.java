package com.commerceapp.admin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AdminRoleUpdateRequest {

    @NotBlank(message = "변경할 역할을 입력하세요.")
    private String role;
}
