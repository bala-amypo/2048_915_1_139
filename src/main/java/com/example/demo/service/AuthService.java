package com.example.demo.service;

import com.example.demo.entity.User;

public interface AuthService {
    User register(String username, String password, String email);
    String login(String username, String password);
}
