package org.example.grade_app.repository;

import org.example.grade_app.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer>{

}
