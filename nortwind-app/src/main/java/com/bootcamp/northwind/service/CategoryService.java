package com.bootcamp.northwind.service;

import com.bootcamp.northwind.model.response.CategoryResponse;
import com.bootcamp.northwind.model.response.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryResponse> getAll();
    Optional<CategoryResponse> getById(Long id);
    Optional<CategoryResponse> save(CategoryResponse response);
    Optional<CategoryResponse> update(CategoryResponse response, Long id);
    Optional<CategoryResponse> delete(Long id);
}
