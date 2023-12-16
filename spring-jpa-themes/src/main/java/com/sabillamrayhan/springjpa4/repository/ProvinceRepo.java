package com.sabillamrayhan.springjpa4.repository;

import com.sabillamrayhan.springjpa4.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProvinceRepo extends JpaRepository<ProvinceEntity,Long> {
    Optional<ProvinceEntity> findByCode(String code);
    List<ProvinceEntity> findByCountryId(Long countryId);
}
