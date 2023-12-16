package com.sabillamrayhan.springjpa4.repository;

import com.sabillamrayhan.springjpa4.entity.AssessmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepo extends JpaRepository<AssessmentEntity, Long> {
}
