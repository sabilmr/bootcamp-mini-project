package com.bootcamp.northwind.service.impl;

import com.bootcamp.northwind.model.entity.CustomerDemographicsEntity;
import com.bootcamp.northwind.model.response.CustomerDemographicsResponse;
import com.bootcamp.northwind.repository.CustomerDemograpRepo;
import com.bootcamp.northwind.service.CustomerDemograpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerDemograpImpl implements CustomerDemograpService {
    private final CustomerDemograpRepo customerDemograpRepo;
    @Override
    public List<CustomerDemographicsResponse> getAll() {
        return customerDemograpRepo.findAll().stream()
                .map(CustomerDemographicsResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDemographicsResponse getById(String id) {
        return customerDemograpRepo.findById(id)
                .map(CustomerDemographicsResponse::new)
                 .orElse(null);
    }

    @Override
    public Optional<CustomerDemographicsResponse> save(CustomerDemographicsResponse response) {
        if (response == null){
            return Optional.empty();
        }

        CustomerDemographicsEntity entity = new CustomerDemographicsEntity(response);
        try {
            customerDemograpRepo.save(entity);
            log.info("Save customer demographics success");
            return Optional.of(new CustomerDemographicsResponse());
        }catch (Exception e){
            log.error("Save customer demographics failed, error : {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<CustomerDemographicsResponse> update(CustomerDemographicsResponse response, String id) {
        CustomerDemographicsEntity entity = customerDemograpRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(response, entity);
        try {
            customerDemograpRepo.save(entity);
            log.info("Update customer demographics success");
            return Optional.of(new CustomerDemographicsResponse());
        }catch (Exception e){
            log.error("Update customer demographics failed, error : {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<CustomerDemographicsResponse> delete(String id) {
        CustomerDemographicsEntity entity = customerDemograpRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        try {
            customerDemograpRepo.delete(entity);
            log.info("Delete customer demographics success");
            return Optional.of(new CustomerDemographicsResponse());
        }catch (Exception e){
            log.error("Delete customer demographics failed, error : {}",e.getMessage());
            return Optional.empty();
        }
    }
}
