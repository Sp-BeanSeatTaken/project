package com.commerceapp.customer.service;

import com.commerceapp.common.exception.NotFoundException;
import com.commerceapp.customer.entity.Customer;
import com.commerceapp.customer.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public Page<Customer> getCustomers(String keyword, String status, int page, int size, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        String keywordParam = (keyword == null) ? "" : keyword;
        String statusParam = (status == null || status.isEmpty()) ? null : status;
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        return customerRepository.findByKeywordAndStatus(keywordParam, statusParam, pageable);
    }

    @Transactional(readOnly = true)
    public Customer getCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 고객이 존재하지 않습니다."));

        if (customer.getIsDeleted()) {
            throw new NotFoundException("삭제 된 고객입니다.");
        }

        return customer;
    }

    @Transactional
    public Customer updateCustomer(Long id, String name, String email, String phoneNumber) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 고객이 존재하지 않습니다."));
        customer.updateCustomers(name, email, phoneNumber);
        return customer;
    }

    @Transactional
    public Customer updateStatus(Long id, String status) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 고객이 존재하지 않습니다."));
        customer.updateStatus(status);
        return customer;
    }

    @Transactional
    public Customer deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 고객이 없습니다"));
        customer.delete();
        return customer;
    }
}
