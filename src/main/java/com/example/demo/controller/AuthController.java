package com.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    public String register() {
        return "registered";
    }

    @PostMapping("/login")
    public String login() {
        return "token";
    }
}
