package org.example.grade_app.repository;

import org.example.grade_app.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Integer>{
}
