package org.example.grade_app.repository;

import org.example.grade_app.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query("""
        select sp.subject
        from SubjectProfessor sp
        where sp.professor.id = :professorId
    """)
    List<Subject> findSubjectsForProfessor(@Param("professorId") Integer professorId);

    @Query("""
        select distinct e.subject
        from Enrollment e
        where e.student.users.id = :userId
    """)
    List<Subject> findSubjectsForStudentUserId(@Param("userId") Integer userId);
}