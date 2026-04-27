package org.example.grade_app.dto;

public record RegisterRequest(
        String email,
        String password,
        String confirm_password
) {}
