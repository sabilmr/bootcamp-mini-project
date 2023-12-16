package com.sabillamrayhan.springjpa4.repository;

import com.sabillamrayhan.springjpa4.entity.AssessmentOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentOptionRepo extends JpaRepository<AssessmentOptionEntity, Long> {
}
