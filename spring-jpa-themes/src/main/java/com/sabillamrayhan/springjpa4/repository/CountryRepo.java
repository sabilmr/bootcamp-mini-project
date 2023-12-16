package com.sabillamrayhan.springjpa4.repository;

import com.sabillamrayhan.springjpa4.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepo extends JpaRepository<CountryEntity, Long> {
    Optional<CountryEntity> findByCode(String code);
}
