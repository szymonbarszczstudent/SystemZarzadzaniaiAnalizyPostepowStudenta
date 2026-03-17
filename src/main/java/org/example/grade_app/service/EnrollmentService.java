package org.example.grade_app.service;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.entity.Enrollment;
import org.example.grade_app.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository EnrollmentRepository;

    public List<Enrollment> getAllEnrollments() {
        return EnrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Integer id) {
        return EnrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
    }
}
