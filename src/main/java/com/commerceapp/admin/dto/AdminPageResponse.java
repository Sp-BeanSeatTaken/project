package com.commerceapp.admin.dto;

import com.commerceapp.admin.entity.Admin;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@JsonPropertyOrder({"page", "size", "adminList", "totalElements", "totalPages"})
@Getter
public class AdminPageResponse {
    private final List<AdminListResponse> adminList;
    private final int page;
    private final int size;
    private final long totalElements;
    private final int totalPages;

    private AdminPageResponse(Page<Admin> adminPage) {
        this.adminList = adminPage.getContent().stream()
                .map(AdminListResponse::from)
                .collect(Collectors.toList());
        this.page = adminPage.getNumber() + 1;
        this.size = adminPage.getSize();
        this.totalElements = adminPage.getTotalElements();
        this.totalPages = adminPage.getTotalPages();
    }

    public static AdminPageResponse from(Page<Admin> adminPage){
        return new AdminPageResponse(adminPage);
    }
}
