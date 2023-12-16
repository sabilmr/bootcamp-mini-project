package com.bootcamp.northwind.service.impl;

import com.bootcamp.northwind.model.entity.EmployeesEntity;
import com.bootcamp.northwind.model.response.EmployeesResponse;
import com.bootcamp.northwind.repository.EmployeesRepo;
import com.bootcamp.northwind.service.EmployeesService;
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
public class EmployeesImpl implements EmployeesService {
    private final EmployeesRepo employeesRepo;
    @Override
    public List<EmployeesResponse> getAll() {
        return employeesRepo.findAll().stream()
                .map(EmployeesResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeesResponse> getById(Long id) {
        EmployeesEntity entity = employeesRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        return Optional.of(new EmployeesResponse(entity));
    }

    @Override
    public Optional<EmployeesResponse> save(EmployeesResponse response) {
        if (response == null){
            return Optional.empty();
        }

        EmployeesEntity entity = new EmployeesEntity(response);
        try {
            employeesRepo.save(entity);
            log.info("Save employees success");
            return Optional.of(new EmployeesResponse());
        } catch (Exception e){
            log.error("Save employees failed, error : {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<EmployeesResponse> update(EmployeesResponse response, Long id) {
        EmployeesEntity entity = employeesRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(response, entity);
        try {
            employeesRepo.save(entity);
            log.info("Update employees success");
            return Optional.of(new EmployeesResponse());
        }catch (Exception e){
            log.error("Update employees failed, error : {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<EmployeesResponse> delete(Long id) {
        EmployeesEntity entity = employeesRepo.findById(id).orElse(null);
        if (entity == null) {
            return Optional.empty();
        }

        try {
            employeesRepo.delete(entity);
            log.info("Delete employees success");
            return Optional.of(new EmployeesResponse());
        } catch (Exception e) {
            log.error("Delete employees failed, error : {}", e.getMessage());
            return Optional.empty();
        }
    }
}
