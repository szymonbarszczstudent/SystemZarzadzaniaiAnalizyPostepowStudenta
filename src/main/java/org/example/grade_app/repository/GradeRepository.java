package org.example.grade_app.repository;

import org.example.grade_app.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Integer> {

    List<Grade> findByEnrollment_Student_Users_Id(Integer userId);

    List<Grade> findByProfessor_Id(Integer professorId);
}
