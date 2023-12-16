package com.bootcamp.northwind.service;

import com.bootcamp.northwind.model.response.CustomerResponse;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerResponse> getAll();
    Optional<CustomerResponse> getById(Long id);
    Optional<CustomerResponse> save(CustomerResponse response);
    Optional<CustomerResponse> update(CustomerResponse response, Long id);
    Optional<CustomerResponse> delete(Long id);
}
