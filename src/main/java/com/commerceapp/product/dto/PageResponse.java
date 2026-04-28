package com.commerceapp.product.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PageResponse<T> {

    // 속성
    private List<T> content;    // 데이터 목록
    private int page;           // 현재 페이지
    private int size;           // 페이지당 개수
    private long totalProducts;  // 전체 상품 수
    private int totalPages;      // 전체 페이지 수


    // 생성자
    public PageResponse(List<T> content, int page, int size, long totalProducts, int totalPages) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalProducts = totalProducts;
        this.totalPages = totalPages;
    }
}
