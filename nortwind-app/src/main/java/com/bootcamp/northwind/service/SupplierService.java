package com.bootcamp.northwind.service;

import com.bootcamp.northwind.model.response.SupplierResponse;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    List<SupplierResponse> getAll();
    Optional<SupplierResponse> getById(Long id);
    Optional<SupplierResponse> save(SupplierResponse request);
    Optional<SupplierResponse> update(SupplierResponse request, Long id);
    Optional<SupplierResponse> delete(Long id);
}
