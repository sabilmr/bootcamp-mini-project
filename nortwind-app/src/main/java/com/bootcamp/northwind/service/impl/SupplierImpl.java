package com.bootcamp.northwind.service.impl;

import com.bootcamp.northwind.model.entity.SupplierEntity;
import com.bootcamp.northwind.model.response.SupplierResponse;
import com.bootcamp.northwind.repository.SupplierRepo;
import com.bootcamp.northwind.service.SupplierService;
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
public class SupplierImpl implements SupplierService {
    private final SupplierRepo supplierRepo;
    @Override
    public List<SupplierResponse> getAll() {
        return supplierRepo.findAll().stream()
                .map(SupplierResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SupplierResponse> getById(Long id) {
        SupplierEntity result = supplierRepo.findById(id).orElse(null);
        if (result == null){
            return Optional.empty();
        }
        return Optional.of(new SupplierResponse(result));
    }

    @Override
    public Optional<SupplierResponse> save(SupplierResponse response) {
        if (response == null){
            return Optional.empty();
        }

        SupplierEntity entity = new SupplierEntity(response);
        try {
            supplierRepo.save(entity);
            log.info("Save supplier success");
            return Optional.of(new SupplierResponse());
        }catch (Exception e){
            log.error("Save supplier failed, error : {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<SupplierResponse> update(SupplierResponse response, Long id) {
        SupplierEntity entity = supplierRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }
        BeanUtils.copyProperties(response, entity);
        try {
            supplierRepo.save(entity);
            log.info("Update supplier success");
            return Optional.of(new SupplierResponse());
        }catch (Exception e){
            log.error("Update supplier failed, error : {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<SupplierResponse> delete(Long id) {
        SupplierEntity entity = supplierRepo.findById(id).orElse(null);
        if (entity == null){
            log.warn("Delete supplier with id not found : {}", id);
            return Optional.empty();
        }
        try {
            supplierRepo.delete(entity);
            log.info("Delete supplier success");
            return Optional.of(new SupplierResponse());
        }catch (Exception e){
            log.error("Delete supplier failed, error : {}",e.getMessage());
            return Optional.empty();
        }
    }
}
