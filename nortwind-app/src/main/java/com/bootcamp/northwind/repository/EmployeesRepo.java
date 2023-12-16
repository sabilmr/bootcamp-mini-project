package com.bootcamp.northwind.repository;

import com.bootcamp.northwind.model.entity.EmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepo extends JpaRepository<EmployeesEntity, Long> {
}
