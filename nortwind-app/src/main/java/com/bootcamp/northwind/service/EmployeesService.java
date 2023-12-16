package com.bootcamp.northwind.service;

import com.bootcamp.northwind.model.response.EmployeesResponse;

import java.util.List;
import java.util.Optional;

public interface EmployeesService {
    List<EmployeesResponse> getAll();
    Optional<EmployeesResponse> getById(Long id);
    Optional<EmployeesResponse> save(EmployeesResponse response);
    Optional<EmployeesResponse> update(EmployeesResponse response, Long id);
    Optional<EmployeesResponse> delete(Long id);
}
