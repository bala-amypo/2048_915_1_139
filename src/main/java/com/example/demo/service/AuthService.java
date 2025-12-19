package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
}
