package com.example.application.report.security;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthorizationService {

    @Override
    public boolean hasPermission(String token, String permission) {

        return false;
    }
}
