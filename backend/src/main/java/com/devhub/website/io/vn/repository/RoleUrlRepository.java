package com.devhub.website.io.vn.repository;

import com.devhub.website.io.vn.entity.RoleUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleUrlRepository extends JpaRepository<RoleUrl, Long> {
    List<RoleUrl> findByRoleId(Long roleId);
}
