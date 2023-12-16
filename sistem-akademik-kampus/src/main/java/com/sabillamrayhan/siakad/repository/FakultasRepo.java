package com.sabillamrayhan.siakad.repository;

import com.sabillamrayhan.siakad.entity.FakultasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FakultasRepo extends JpaRepository<FakultasEntity, String> {
}
