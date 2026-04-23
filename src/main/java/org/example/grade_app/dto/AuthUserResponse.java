package org.example.grade_app.dto;

public record AuthUserResponse(
        boolean loggedIn,
        Integer userId,
        String email,
        String role
) {}
