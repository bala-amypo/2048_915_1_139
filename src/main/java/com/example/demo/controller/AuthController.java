package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public String login() {
        return "Login API";
    }

    @PostMapping("/register")
    public String register() {
        return "Register API";
    }
}
