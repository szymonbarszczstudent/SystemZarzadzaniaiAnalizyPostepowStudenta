package org.example.grade_app.service;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.dto.EnrollmentOptionDto;
import org.example.grade_app.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Transactional(readOnly = true)
    public List<EnrollmentOptionDto> getEnrollmentOptionsForProfessor(Integer professorUserId) {
        return enrollmentRepository.findAll()
                .stream()
                .map(e -> new EnrollmentOptionDto(
                        e.getId(),
                        e.getStudent().getStudentNumber(),
                        e.getStudent().getFirstName(),
                        e.getStudent().getLastName(),
                        e.getSubject().getCode(),
                        e.getSubject().getName(),
                        e.getStudent().getProgramName()
                ))
                .toList();
    }
}