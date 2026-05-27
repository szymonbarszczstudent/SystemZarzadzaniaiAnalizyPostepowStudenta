package org.example.grade_app.repository;

import org.example.grade_app.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    List<Enrollment> findByStudent_Users_Id(Integer userId);
    List<Enrollment> findAll();
    @Query("""
    select e
    from Enrollment e
    join SubjectProfessor sp on sp.subject = e.subject
    where sp.professor.id = :professorUserId
""")
    List<Enrollment> findEnrollmentsForProfessor(@Param("professorUserId") Integer professorUserId);
}