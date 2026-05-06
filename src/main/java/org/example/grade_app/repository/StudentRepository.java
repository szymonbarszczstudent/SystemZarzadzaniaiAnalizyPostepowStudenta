package org.example.grade_app.repository;

import org.example.grade_app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer>{
    Optional<Student> findByUsers_Id(Integer userId);
}