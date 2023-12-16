package com.bootcamp.northwind.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user")
public class UserEntity {
    @Id
    @Column(name = "ID", length = 36)
    private String id;

    @Column(name = "FIRST_NAME", length = 32)
    private String firstName;

    @Column(name = "LAST_NAME", length = 32)
    private String lastName;

    @Column(name = "USERNAME", length = 128, unique = true)
    private String username;

    @Column(name = "EMAIL", length = 128, unique = true)
    private String email;

    @Column(name = "PASSWORD", length = 1028)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<RoleEntity> roles = new ArrayList<>();

    public UserEntity(String firstName, String lastName, String email, String password, List<RoleEntity> roles) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = email;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
