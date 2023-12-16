package com.sabillamrayhan.siakad.repository;

import com.sabillamrayhan.siakad.entity.JurusanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JurusanRepo extends JpaRepository<JurusanEntity, String> {
}
