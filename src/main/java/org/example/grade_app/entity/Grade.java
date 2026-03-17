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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "grades", schema = "grades_app")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @Column(name = "category", nullable = false, length = 80)
    private String category;

    @Column(name = "grade_value", nullable = false, precision = 3, scale = 1)
    private BigDecimal gradeValue;

    @ColumnDefault("1.000")
    @Column(name = "weight", nullable = false, precision = 6, scale = 3)
    private BigDecimal weight;

    @Lob
    @Column(name = "comment")
    private String comment;

    @ColumnDefault("current_timestamp()")
    @Column(name = "graded_at", nullable = false)
    private Instant gradedAt;
}