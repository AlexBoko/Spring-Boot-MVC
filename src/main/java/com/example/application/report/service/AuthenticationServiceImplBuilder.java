package com.example.application.report.service;

import org.springframework.security.authentication.AuthenticationManager;

public class AuthenticationServiceImplBuilder {
    private AuthenticationManager authenticationManager;

    public AuthenticationServiceImplBuilder setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        return this;
    }

    public AuthenticationServiceImpl createAuthenticationServiceImpl() {
        return new AuthenticationServiceImpl(authenticationManager);
    }
}