package com.devhub.website.io.vn.jwt.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role")
    private List<UserEntity> users;
}