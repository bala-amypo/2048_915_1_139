package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User registration request payload")
public class RegisterRequest {

    @Schema(description = "Username", example = "student01")
    private String username;

    @Schema(description = "Password", example = "password123")
    private String password;

    @Schema(description = "User role", example = "ROLE_USER")
    private String role;

    public RegisterRequest() {
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
