package org.example.grade_app.repository;

import org.example.grade_app.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer>{
}
