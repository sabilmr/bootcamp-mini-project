package com.sabillamrayhan.siakad.repository;

import com.sabillamrayhan.siakad.entity.RuangEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuangRepo extends JpaRepository<RuangEntity, String> {
}
