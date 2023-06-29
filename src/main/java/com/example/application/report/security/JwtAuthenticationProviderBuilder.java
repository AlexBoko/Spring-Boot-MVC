package com.example.application.report.security;

import com.example.application.report.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class JwtAuthenticationProviderBuilder {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public JwtAuthenticationProviderBuilder setUserService(UserService userService) {
        this.userService = userService;
        return this;
    }

    public JwtAuthenticationProviderBuilder setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        return this;
    }

    public JwtAuthenticationProvider createJwtAuthenticationProvider() {
        return new JwtAuthenticationProvider(userService, passwordEncoder);
    }
}