package com.bootcamp.northwind.repository;

import com.bootcamp.northwind.model.entity.LookUpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LookupRepo extends JpaRepository<LookUpEntity, Long> {
    List<LookUpEntity> findByGroups(String groups);
    Optional<LookUpEntity> findByCode(String code);
}
