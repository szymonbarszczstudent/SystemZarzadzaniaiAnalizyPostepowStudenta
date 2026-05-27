package org.example.grade_app.repository;

import org.example.grade_app.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    Optional<Professor> findByUsers_Id(Integer userId);
}