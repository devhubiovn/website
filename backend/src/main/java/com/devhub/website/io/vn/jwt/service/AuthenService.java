package com.devhub.website.io.vn.jwt.service;

import java.util.List;

import com.devhub.website.io.vn.dto.RoleDTO;
import com.devhub.website.io.vn.jwt.plugins.request.AuthenRequest;

public interface AuthenService {
    List<RoleDTO> checkLogin(AuthenRequest request);
}