package org.example.grade_app.service;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.entity.Subject;
import org.example.grade_app.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository SubjectRepository;
    public List<Subject> getAllSubjects() {
        return SubjectRepository.findAll();
    }

    public Subject getSubjectById(Integer id) {
        return SubjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
    }
}
