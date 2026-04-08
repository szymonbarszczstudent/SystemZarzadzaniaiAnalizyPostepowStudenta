package org.example.grade_app.dto;

import java.time.Instant;

public record UserDto(
        String email,
        String role,
        Instant createdAt
) {}
