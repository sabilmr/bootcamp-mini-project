package com.sabillamrayhan.springjpa4.service.impl;

import com.sabillamrayhan.springjpa4.entity.CustomerAddressEntity;
import com.sabillamrayhan.springjpa4.entity.CustomerEntity;
import com.sabillamrayhan.springjpa4.model.CustomerAddressModel;
import com.sabillamrayhan.springjpa4.model.CustomerModel;
import com.sabillamrayhan.springjpa4.repository.CustomerAddressRepo;
import com.sabillamrayhan.springjpa4.repository.CustomerRepo;
import com.sabillamrayhan.springjpa4.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerAddressRepo customerAddressRepo;

    @Override
    public Page<CustomerModel> getPage() {
        Pageable pageable = PageRequest.of(0,10);
        Page<CustomerEntity> pages = customerRepo.findAll(pageable);

        List<CustomerModel> list = pages.getContent().stream().map(CustomerModel::new)
                .collect(Collectors.toList());

        Page<CustomerModel> result = new PageImpl<CustomerModel>(list, pageable, pages.getTotalElements());
        return result;
    }

    @Override
    public List<CustomerModel> gets() {
        List<CustomerEntity> result = customerRepo.findAll();
        if (result.isEmpty()){
            return Collections.emptyList();
        }

        return result.stream()
                .map(CustomerModel::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerModel> getById(Long id) {
        CustomerEntity entity = customerRepo.findById(id).orElse(null);
        if (entity == null)
            Optional.empty();

        CustomerModel model = new CustomerModel(entity);
        return Optional.of(model);
    }

    @Override
    public void save(CustomerModel request) {
        if (customerRepo.existsByAccountNo(request.getAccountNo())) {
            log.warn("Save customer failed, error account number with {} is exist", request.getAccountNo());
            return;
        }

        CustomerEntity entity = new CustomerEntity();
        BeanUtils.copyProperties(request, entity);

        if (!request.getAddress().isEmpty()){
            for (CustomerAddressModel addressModel: request.getAddress()){
                CustomerAddressEntity addressEntity = new CustomerAddressEntity();

                BeanUtils.copyProperties(addressModel, addressEntity);

                entity.addAddress(addressEntity);
            }
        }
        try {
            customerRepo.save(entity);
            log.info("Save customer success");
        }catch (Exception sabil){
            log.error("Save customer failed, error: {}",sabil.getMessage());
        }
    }

    @Override
    public void saveAddress(CustomerAddressModel request) {
        if (request.getCustomerId() == 0L){
            return;
        }

        CustomerEntity customer = customerRepo.findById(request.getCustomerId()).orElse(null);
        if (customer == null){
            return;
        }

        CustomerAddressEntity entity = new CustomerAddressEntity();
        BeanUtils.copyProperties(request, entity);
        entity.setCustomer(customer);
        try {
            customerAddressRepo.save(entity);
            log.info("Save customer address success");
        }catch (Exception sabil){
            log.error("Save customer address failed, error: {}",sabil.getMessage());
        }
    }

    @Override
    public void update(CustomerModel request, Long id) {
        CustomerEntity entity = customerRepo.findById(id).orElse(null);
        if (entity == null) {
            return;
        }
        BeanUtils.copyProperties(request,entity);
        entity.setId(id);
        try {
            customerRepo.save(entity);
            log.info("Update customer success");
        }catch (Exception sabil){
            log.error("Update customer failed, error: {}",sabil.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        CustomerEntity entity = customerRepo.findById(id).orElse(null);
        if (entity == null){
            return;
        }
        try {
            customerRepo.delete(entity);
            log.info("Delete customer success");
        }catch (Exception sabil){
            log.error("Delete customer failed, error: {}",sabil.getMessage());
        }
    }
}
