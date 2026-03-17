package org.example.grade_app.service;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.entity.Professor;
import org.example.grade_app.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository ProfessorRepository;

    public List<Professor> getAllProfessors() {
        return ProfessorRepository.findAll();
    }

    public Professor getProfessorById(Integer id) {
        return ProfessorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
    }
}
