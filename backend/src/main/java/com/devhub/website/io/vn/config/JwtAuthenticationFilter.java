package com.devhub.website.io.vn.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String token = jwtProvider.resolveToken(request);  // Lấy token từ request
        if (token != null && jwtProvider.validateToken(token)) {  // Kiểm tra tính hợp lệ của token
            Authentication auth = jwtProvider.getAuthentication(token);  // Lấy Authentication từ token
            SecurityContextHolder.getContext().setAuthentication(auth);  // Thiết lập Authentication trong SecurityContext
        }
        
        // Tiếp tục chuỗi lọc
        filterChain.doFilter(request, response);
    }
}
