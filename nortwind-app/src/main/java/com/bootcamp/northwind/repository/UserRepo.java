package com.bootcamp.northwind.repository;

import com.bootcamp.northwind.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByUsernameOrEmail(String username, String Email);

}
