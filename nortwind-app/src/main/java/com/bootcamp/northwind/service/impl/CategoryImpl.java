package com.bootcamp.northwind.service.impl;

import com.bootcamp.northwind.model.entity.CategoryEntity;
import com.bootcamp.northwind.model.entity.ProductEntity;
import com.bootcamp.northwind.model.response.CategoryResponse;
import com.bootcamp.northwind.model.response.ProductResponse;
import com.bootcamp.northwind.repository.CategoryRepo;
import com.bootcamp.northwind.repository.ProductRepo;
import com.bootcamp.northwind.service.CategoryService;
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
public class CategoryImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;
    @Override
    public List<CategoryResponse> getAll() {
        return categoryRepo.findAll().stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryResponse> getById(Long id){
       CategoryEntity entity = categoryRepo.findById(id).orElse(null);
       if (entity == null){
           return Optional.empty();
       }

       CategoryResponse response =new CategoryResponse(entity);
       return Optional.of(response);
    }

    @Override
    public Optional<CategoryResponse> save(CategoryResponse response) {
        CategoryEntity entity = new CategoryEntity();
        BeanUtils.copyProperties(response, entity);

        if (!response.getProduct().isEmpty()){
            for (ProductResponse productResponse: response.getProduct()){
                ProductEntity product = new ProductEntity();

                BeanUtils.copyProperties(productResponse, product);
                entity.addProduct(product);
            }
        }
        try {
            categoryRepo.save(entity);
            log.info("Save category success");
            return Optional.of(new CategoryResponse());
        }catch (Exception e){
            log.error("Save category failed, error : {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<CategoryResponse> update(CategoryResponse response, Long id) {
        CategoryEntity entity = categoryRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(response, entity);
        try {
            categoryRepo.save(entity);
            log.info("Update category success");
            return Optional.of(new CategoryResponse());
        }catch (Exception e){
            log.error("Update category failed, error : {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<CategoryResponse> delete(Long id) {
        CategoryEntity entity = categoryRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        try {
            categoryRepo.delete(entity);
            log.info("Delete category success");
            return Optional.of(new CategoryResponse());
        }catch (Exception e){
            log.error("Delete category failed, error : {}",e.getMessage());
            return Optional.empty();
        }
    }
}
