package org.example.grade_app.dto;

public record StudentDto(
        String studentNumber,
        String firstName,
        String lastName,
        Byte studyYear,
        String programName
) {}