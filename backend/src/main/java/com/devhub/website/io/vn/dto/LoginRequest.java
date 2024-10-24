package com.devhub.website.io.vn.dto;

public class LoginRequest {

    private String username;
    private String password;

    // Constructor không tham số
    public LoginRequest() {
    }

    // Constructor có tham số
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters và Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}