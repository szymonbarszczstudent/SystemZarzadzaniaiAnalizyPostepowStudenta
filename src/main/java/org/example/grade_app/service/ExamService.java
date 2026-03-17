package org.example.grade_app.service;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.entity.Exam;
import org.example.grade_app.repository.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository ExamRepository;

    public List<Exam> getAllExams() {
        return ExamRepository.findAll();
    }

    public Exam getExamById(Integer id) {
        return ExamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
    }
}
