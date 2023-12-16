package com.sabillamrayhan.siakad.repository;

import com.sabillamrayhan.siakad.entity.MataKuliahEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MataKuliahRepo extends JpaRepository<MataKuliahEntity, String> {
}
