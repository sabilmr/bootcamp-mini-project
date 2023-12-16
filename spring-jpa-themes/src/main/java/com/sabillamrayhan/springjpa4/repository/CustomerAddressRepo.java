package com.sabillamrayhan.springjpa4.repository;

import com.sabillamrayhan.springjpa4.entity.CustomerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressRepo extends JpaRepository<CustomerAddressEntity, Long> {
}
