package org.example.grade_app.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record CreateGradeRequest(
        Integer enrollmentId,
        Integer professorId,
        String category,
        BigDecimal gradeValue,
        BigDecimal weight,
        String comment,
        Instant gradedAt
) {}
