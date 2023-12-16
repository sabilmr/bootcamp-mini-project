package com.sabillamrayhan.siakad.repository;

import com.sabillamrayhan.siakad.entity.KelasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KelasRepo extends JpaRepository<KelasEntity, String> {
}
