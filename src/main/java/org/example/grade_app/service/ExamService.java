package org.example.grade_app.service;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.dto.CreateExamRequest;
import org.example.grade_app.dto.DtoMapper;
import org.example.grade_app.dto.ExamDto;
import org.example.grade_app.entity.Enrollment;
import org.example.grade_app.entity.Exam;
import org.example.grade_app.entity.Professor;
import org.example.grade_app.repository.EnrollmentRepository;
import org.example.grade_app.repository.ExamRepository;
import org.example.grade_app.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository ExamRepository;
    private final EnrollmentRepository EnrollmentRepository;
    private final ProfessorRepository ProfessorRepository;
    @Transactional(readOnly = true)
    public List<ExamDto> getAllExams() {
        return ExamRepository.findAll()
                .stream()
                .map(DtoMapper::toExamDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public ExamDto getExamById(Integer id) {
        return ExamRepository.findById(id)
                .map(DtoMapper::toExamDto)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
    }

    @Transactional
    public ExamDto createExam(CreateExamRequest request, Integer professorUserId) {
        Enrollment enrollment = EnrollmentRepository.findById(request.enrollmentId())
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        Professor professor = ProfessorRepository.findById(professorUserId)
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        Exam exam = new Exam();
        exam.setEnrollment(enrollment);
        exam.setProfessor(professor);
        exam.setAttemptNumber(request.attemptNumber());
        exam.setExamDate(request.examDate());
        String status = request.status().toUpperCase();

        if (!List.of("PASSED", "FAILED", "ABSENT", "CANCELLED").contains(status)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status: " + request.status());
        }

        exam.setStatus(status);
        exam.setGradeValue(request.gradeValue());
        exam.setComment(request.comment());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        exam.setRecordedAt(LocalDateTime.now().format(formatter));

        Exam saved = ExamRepository.save(exam);
        return DtoMapper.toExamDto(saved);
    }

    public List<Exam> getAllExamsAdmin() {
        return ExamRepository.findAll();
    }

    public Exam getExamByIdAdmin(Integer id) {
        return ExamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
    }
    @Transactional(readOnly = true)
    public List<ExamDto> getExamsForProfessorUserId(Integer userId) {
        return ExamRepository.findByProfessor_Id(userId)
                .stream()
                .map(DtoMapper::toExamDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ExamDto> getExamsForStudentUserId(Integer userId) {
        return ExamRepository.findByEnrollment_Student_Users_Id(userId)
                .stream()
                .map(DtoMapper::toExamDto)
                .toList();
    }

}
