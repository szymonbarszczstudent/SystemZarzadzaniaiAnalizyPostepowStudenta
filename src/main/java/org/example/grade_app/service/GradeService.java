package org.example.grade_app.service;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.dto.CreateGradeRequest;
import org.example.grade_app.dto.DtoMapper;
import org.example.grade_app.dto.GradeDto;
import org.example.grade_app.entity.Enrollment;
import org.example.grade_app.entity.Grade;
import org.example.grade_app.entity.Professor;
import org.example.grade_app.repository.EnrollmentRepository;
import org.example.grade_app.repository.GradeRepository;
import org.example.grade_app.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository GradeRepository;
    private final EnrollmentRepository EnrollmentRepository;
    private final ProfessorRepository ProfessorRepository;
    @Transactional(readOnly = true)
    public List<GradeDto> getAllGrades() {
        return GradeRepository.findAll()
                .stream()
                .map(DtoMapper::toGradeDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public GradeDto getGradeById(Integer id) {
        return GradeRepository.findById(id)
                .map(DtoMapper::toGradeDto)
                .orElseThrow(() -> new RuntimeException("Grade not found"));
    }

    @Transactional
    public GradeDto createGrade(CreateGradeRequest request) {
        Enrollment enrollment = EnrollmentRepository.findById(request.enrollmentId())
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        Professor professor = ProfessorRepository.findById(request.professorId())
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        Grade grade = new Grade();
        grade.setEnrollment(enrollment);
        grade.setProfessor(professor);
        grade.setCategory(request.category());
        grade.setGradeValue(request.gradeValue());
        grade.setWeight(request.weight());
        grade.setComment(request.comment());
        grade.setGradedAt(request.gradedAt());

        Grade saved = GradeRepository.save(grade);
        return DtoMapper.toGradeDto(saved);
    }

    public List<Grade> getAllGradesAdmin() {
        return GradeRepository.findAll();
    }

    public Grade getGradeByIdAdmin(Integer id) {
        return GradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found"));
    }
}
