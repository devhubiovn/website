package com.devhub.website.io.vn.jwt.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "user")
@Data // Tự tạo getter setter thư viện lombok
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private RoleEntity role;
}