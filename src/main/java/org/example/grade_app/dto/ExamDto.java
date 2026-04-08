package org.example.grade_app.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record ExamDto(
        String studentNumber,
        String subjectCode,
        String subjectName,
        String professorLastName,
        Byte attemptNumber,
        String examDate,
        String status,
        BigDecimal gradeValue,
        String comment,
        Instant recordedAt
) {}
