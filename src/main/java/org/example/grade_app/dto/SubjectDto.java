package org.example.grade_app.dto;

public record SubjectDto(
        String code,
        String name,
        Byte ects,
        String description
) {}
