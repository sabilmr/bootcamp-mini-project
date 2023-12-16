package com.bootcamp.northwind.service.impl;

import com.bootcamp.northwind.model.entity.ProductEntity;
import com.bootcamp.northwind.model.response.ProductResponse;
import com.bootcamp.northwind.repository.ProductRepo;
import com.bootcamp.northwind.service.ProductService;
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
public class ProductImpl implements ProductService {
    private final ProductRepo productRepo;

    @Override
    public List<ProductResponse> getAll() {
        return productRepo.findAll().stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductResponse> getById(Long id) {
        ProductEntity entity = productRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        return Optional.of(new ProductResponse(entity));
    }

    @Override
    public Optional<ProductResponse> save(ProductResponse response) {
        if (response == null){
            return Optional.empty();
        }

        ProductEntity entity = new ProductEntity();
        try {
            productRepo.save(entity);
            log.info("Save Product success");
            return Optional.of(new ProductResponse());
        }catch (Exception e){
            log.error("Save Product failed, error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProductResponse> update(ProductResponse response, Long id) {
        ProductEntity entity = productRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(response, entity);
        entity.setId(id);
        try {
            productRepo.save(entity);
            log.info("Update product success");
            return Optional.of(new ProductResponse());
        }catch (Exception e){
            log.error("Update product failed, error : {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProductResponse> delete(Long id) {
        ProductEntity entity = productRepo.findById(id).orElse(null);
        if (entity == null){
            log.warn("Delete product with id not found : {}", id);
            return Optional.empty();
        }
        try {
            productRepo.delete(entity);
            log.info("Delete product success");
            return Optional.of(new ProductResponse());
        }catch (Exception e){
            log.error("Delete product failed, error : {}",e.getMessage());
            return Optional.empty();
        }
    }
}
