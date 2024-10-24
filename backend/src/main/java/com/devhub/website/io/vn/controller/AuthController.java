package com.devhub.website.io.vn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devhub.website.io.vn.dto.LoginRequest;
import com.devhub.website.io.vn.dto.RegisterRequest;
import com.devhub.website.io.vn.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Hiển thị form đăng ký
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // Trả về file templates/register.html
    }

    // Xử lý đăng ký
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest, Model model) {
        authService.register(registerRequest);
        model.addAttribute("message", "User registered successfully");
        return "register"; // Trả về lại trang register.html với message
    }

    // Hiển thị form đăng nhập
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Trả về file templates/login.html
    }

    // Xử lý đăng nhập
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest, Model model) {
        String token = authService.login(loginRequest);
        model.addAttribute("token", token); // Thêm token vào model
        return "dashboard"; // Trả về file templates/dashboard.html (nếu có) sau khi đăng nhập
    }
}
