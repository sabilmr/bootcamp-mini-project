package com.bootcamp.northwind.service.impl;

import com.bootcamp.northwind.model.entity.CustomerEntity;
import com.bootcamp.northwind.model.response.CustomerResponse;
import com.bootcamp.northwind.repository.CustomerRepo;
import com.bootcamp.northwind.service.CustomerService;
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
public class CustomerImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    @Override
    public List<CustomerResponse> getAll() {
        return customerRepo.findAll().stream()
                .map(CustomerResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerResponse> getById(Long id) {
        CustomerEntity entity = customerRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        return Optional.of(new CustomerResponse(entity));
    }

    @Override
    public Optional<CustomerResponse> save(CustomerResponse response) {
        if (response == null){
            return Optional.empty();
        }

        CustomerEntity entity = new CustomerEntity(response);
        try {
            customerRepo.save(entity);
            log.info("Save customer success");
            return Optional.of(new CustomerResponse());
        }catch (Exception e){
            log.error("Save customer failed, error : {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<CustomerResponse> update(CustomerResponse response, Long id) {
        CustomerEntity entity = customerRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(response, entity);
        try {
            customerRepo.save(entity);
            log.info("Update customer success");
            return Optional.of(new CustomerResponse());
        }catch (Exception e){
            log.error("Update customer failed, error : {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<CustomerResponse> delete(Long id) {
        CustomerEntity entity = customerRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        try {
            customerRepo.delete(entity);
            log.info("Delete customer success");
            return Optional.of(new CustomerResponse());
        }catch (Exception e){
            log.error("Delete customer failed, error : {}",e.getMessage());
            return Optional.empty();
        }
    }
}
