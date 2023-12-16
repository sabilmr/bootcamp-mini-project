package com.bootcamp.northwind.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lookup_tab")
public class LookUpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "lookup_group", length = 64, nullable = false)
    private String groups;

    @Column(name = "lookup_code", length = 20, nullable = false)
    private String code;

    @Column(name = "lookup_name", length = 100, nullable = false)
    private String name;

    @Column(name = "lookup_position", nullable = false)
    private Integer position;

    @Column(name = "lookup_active", nullable = false)
    private Boolean active;

    public LookUpEntity(String groups, String code, String name, Integer position) {
        this.groups = groups;
        this.code = code;
        this.name = name;
        this.position = position;
        this.active = true;
    }
}
