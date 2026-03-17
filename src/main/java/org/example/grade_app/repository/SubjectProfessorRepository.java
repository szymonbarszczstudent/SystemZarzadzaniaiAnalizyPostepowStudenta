package org.example.grade_app.repository;

import org.example.grade_app.entity.SubjectProfessor;
import org.example.grade_app.entity.SubjectProfessorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectProfessorRepository extends JpaRepository<SubjectProfessor, SubjectProfessorId>{
}
