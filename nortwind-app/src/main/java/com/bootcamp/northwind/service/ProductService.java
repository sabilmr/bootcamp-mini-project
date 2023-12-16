package com.bootcamp.northwind.service;

import com.bootcamp.northwind.model.response.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponse> getAll();
    Optional<ProductResponse> getById(Long id);
    Optional<ProductResponse> save(ProductResponse response);
    Optional<ProductResponse> update(ProductResponse response, Long id);
    Optional<ProductResponse> delete(Long id);
}
