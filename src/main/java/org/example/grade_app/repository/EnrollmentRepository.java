package org.example.grade_app.repository;

import org.example.grade_app.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{
}
