package com.sabillamrayhan.siakad.repository;

import com.sabillamrayhan.siakad.entity.DosenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DosenRepo extends JpaRepository<DosenEntity, String> {
}
