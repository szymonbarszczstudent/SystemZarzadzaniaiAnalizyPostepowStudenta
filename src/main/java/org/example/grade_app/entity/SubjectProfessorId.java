package org.example.grade_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class SubjectProfessorId implements Serializable {
    private static final long serialVersionUID = 1474978957734085684L;
    @Column(name = "subject_id", nullable = false)
    private Integer subjectId;

    @Column(name = "professor_id", nullable = false)
    private Integer professorId;

}