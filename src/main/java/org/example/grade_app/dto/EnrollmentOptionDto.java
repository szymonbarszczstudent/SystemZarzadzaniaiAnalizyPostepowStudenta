package org.example.grade_app.dto;

public record EnrollmentOptionDto(
        Integer enrollmentId,
        String studentNumber,
        String firstName,
        String lastName,
        String subjectCode,
        String subjectName
) {}