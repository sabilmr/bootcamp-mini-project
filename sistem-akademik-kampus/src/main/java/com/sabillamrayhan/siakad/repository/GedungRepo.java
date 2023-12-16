package com.sabillamrayhan.siakad.repository;

import com.sabillamrayhan.siakad.entity.GedungEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GedungRepo extends JpaRepository<GedungEntity, String> {
}
