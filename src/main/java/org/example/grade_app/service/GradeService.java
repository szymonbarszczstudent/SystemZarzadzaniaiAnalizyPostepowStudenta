package org.example.grade_app.service;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.entity.Grade;
import org.example.grade_app.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository GradeRepository;

    public List<Grade> getAllGrades() {
        return GradeRepository.findAll();
    }

    public Grade getGradeById(Integer id) {
        return GradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found"));
    }
}
