package org.example.grade_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @Column(name = "attempt_number", nullable = false)
    private Byte attemptNumber;

    @Column(name = "exam_date")
    private String examDate;

    @ColumnDefault("'FAILED'")
    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "grade_value", precision = 3, scale = 1)
    private BigDecimal gradeValue;

    @Lob
    @Column(name = "comment")
    private String comment;

    @ColumnDefault("current_timestamp()")
    @Column(name = "recorded_at", nullable = false)
    private Instant recordedAt;

}