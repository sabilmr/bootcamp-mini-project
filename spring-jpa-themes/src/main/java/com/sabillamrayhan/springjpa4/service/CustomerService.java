package com.sabillamrayhan.springjpa4.service;

import com.sabillamrayhan.springjpa4.model.CustomerAddressModel;
import com.sabillamrayhan.springjpa4.model.CustomerModel;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Page<CustomerModel> getPage();
    List<CustomerModel> gets();
    Optional<CustomerModel> getById(Long id);
    void save(CustomerModel request);
    void saveAddress(CustomerAddressModel request);
    void update(CustomerModel request, Long id);
    void delete(Long id);
}
