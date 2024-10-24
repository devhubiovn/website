package com.devhub.website.io.vn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devhub.website.io.vn.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}