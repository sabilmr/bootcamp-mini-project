package com.bootcamp.northwind.service;

import com.bootcamp.northwind.model.response.CustomerDemographicsResponse;

import java.util.List;
import java.util.Optional;

public interface CustomerDemograpService {
    List<CustomerDemographicsResponse> getAll();
    CustomerDemographicsResponse getById(String id);
    Optional<CustomerDemographicsResponse> save(CustomerDemographicsResponse response);
    Optional<CustomerDemographicsResponse> update(CustomerDemographicsResponse response, String id);
    Optional<CustomerDemographicsResponse> delete(String id);
}
