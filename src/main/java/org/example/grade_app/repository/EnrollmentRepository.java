package org.example.grade_app.repository;

import org.example.grade_app.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{
    List<Enrollment> findByStudent_Users_Id(Integer userId);
}
