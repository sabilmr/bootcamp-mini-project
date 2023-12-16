package com.sabillamrayhan.springjpa4.repository;

import com.sabillamrayhan.springjpa4.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Long> {
    boolean existsByAccountNo(Long accountNo);
}
