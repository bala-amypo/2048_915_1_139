package com.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Auth")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

   
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestBody Map<String, String> request) {
        return ResponseEntity.ok(Map.of("message", "Login successful"));
    }

    
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(
            @RequestBody Map<String, String> request) {
        return ResponseEntity.ok(Map.of("message", "Registration successful"));
    }
}
