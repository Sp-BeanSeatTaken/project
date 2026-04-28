package com.commerceapp.product.controller;

import com.commerceapp.product.dto.*;
import com.commerceapp.product.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    // 상품 등록
    @PostMapping
    public ResponseEntity<ProductDetailResponse> create(
            @RequestBody ProductCreateRequest request,
            HttpSession session
    ) {

        return ResponseEntity.ok(productService.create(request, session));
    }

    // 상품 전체 조회
    @GetMapping
    public PageResponse<ProductResponse> getProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String state,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        return productService.getAllproducts(keyword, category, state, page, size, sortBy, direction);
    }

    // 상품 상세 조회
    @GetMapping("/{productId}")
    public ProductDetailResponse getDetail(@PathVariable Long productId) {
        return productService.getDetail(productId);
    }

    // 상품 수정
    @PatchMapping("/{productId}")
    public void update(@PathVariable Long productId,
                       @RequestBody ProductUpdateRequest request) {
        // 수정할 데이터 꺼내기
        productService.update(
                productId,
                request.getName(),
                request.getCategory(),
                request.getPrice(),
                request.getStock()
        );
    }

    // 상품 삭제
    @DeleteMapping("/{productId}")
    public void delete(@PathVariable Long productId) {
        productService.delete(productId);
    }
}
