package com.sabillamrayhan.springjpa4.repository;

import com.sabillamrayhan.springjpa4.entity.SubDistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubDistrictRepo extends JpaRepository<SubDistrictEntity, Long> {
    List<SubDistrictEntity> findByDistrictId(Long provinceId);
}
