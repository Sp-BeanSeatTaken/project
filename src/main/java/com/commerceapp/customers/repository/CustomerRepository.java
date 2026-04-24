package com.commerceapp.customers.repository;

import com.commerceapp.customers.entity.Customers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customers, Long> {
    /* 이메일 중복 확인 */
    boolean existsByEmail(String email);


    @Query("SELECT c FROM Customers c WHERE (c.name LIKE %:keyword% OR c.email LIKE %:keyword%) AND (:status IS NULL OR c.status = :status)")
    Page<Customers> findByKeywordAndStatus(@Param("keyword") String keyword, @Param("status") String status, Pageable pageable);

}

