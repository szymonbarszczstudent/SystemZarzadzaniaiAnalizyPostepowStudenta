package org.example.grade_app.repository;

import org.example.grade_app.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Integer> {

    List<Exam> findByEnrollment_Student_Users_Id(Integer userId);

    List<Exam> findByProfessor_Id(Integer professorId);
}
