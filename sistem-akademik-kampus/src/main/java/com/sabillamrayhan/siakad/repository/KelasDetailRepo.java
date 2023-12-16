package com.sabillamrayhan.siakad.repository;

import com.sabillamrayhan.siakad.entity.KelasDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KelasDetailRepo extends JpaRepository<KelasDetailEntity, String> {
}
