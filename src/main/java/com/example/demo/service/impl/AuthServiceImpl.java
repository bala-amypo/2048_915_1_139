package com.example.demo.service.impl;

import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String register(String username, String password) {
        return "User registered successfully";
    }

    @Override
    public String login(String username, String password) {
        return "Login successful";
    }
}
