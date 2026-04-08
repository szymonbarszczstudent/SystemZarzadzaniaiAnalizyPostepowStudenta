package org.example.grade_app.dto;

import java.math.BigDecimal;

public record CreateExamRequest(
        Integer enrollmentId,
        Integer professorId,
        Byte attemptNumber,
        String examDate,
        String status,
        BigDecimal gradeValue,
        String comment
) {}
