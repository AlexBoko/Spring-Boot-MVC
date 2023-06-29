package com.example.application.report.service;

import com.example.application.report.model.User;

public interface UserService {
    User createUser(User user);
    User getUserByUsername(String username);
}
