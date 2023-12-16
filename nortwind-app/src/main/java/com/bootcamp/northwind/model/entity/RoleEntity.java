package com.bootcamp.northwind.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_role")
public class RoleEntity {
    @Id
    @Column(name = "ID", length = 36)
    private String id;

    @Column(name = "ROLE_NAME", length = 32)
    private String name;

    public RoleEntity(String name){
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
