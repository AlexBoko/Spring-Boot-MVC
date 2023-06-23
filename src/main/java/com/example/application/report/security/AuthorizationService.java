package com.example.application.report.security;

public interface AuthorizationService {
    boolean hasPermission(String token, String permission);
}
