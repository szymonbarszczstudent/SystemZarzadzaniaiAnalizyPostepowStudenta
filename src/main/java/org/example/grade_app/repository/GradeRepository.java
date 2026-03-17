package org.example.grade_app.repository;

import org.example.grade_app.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Integer>{
}
