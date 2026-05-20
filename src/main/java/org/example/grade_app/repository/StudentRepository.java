package org.example.grade_app.repository;

import org.example.grade_app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface StudentRepository extends JpaRepository<Student, Integer>{
    Optional<Student> findByUsers_Id(Integer userId);
    @Query("""
    select distinct s
    from Student s
    join Enrollment e on e.student = s
    join SubjectProfessor sp on sp.subject = e.subject
    where sp.professor.id = :professorId
""")
    List<Student> findStudentsForProfessor(@Param("professorId") Integer professorId);
}