package org.example.grade_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name = "academic_year", length = 9)
    private String academicYear;

    @Lob
    @Column(name = "semester")
    private String semester;

    @ColumnDefault("'ENROLLED'")
    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @ColumnDefault("current_timestamp()")
    @Column(name = "enrolled_at", nullable = false)
    private Instant enrolledAt;

}