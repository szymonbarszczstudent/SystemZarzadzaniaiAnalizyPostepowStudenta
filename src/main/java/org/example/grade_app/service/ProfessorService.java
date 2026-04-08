package org.example.grade_app.service;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.dto.DtoMapper;
import org.example.grade_app.dto.ProfessorDto;
import org.example.grade_app.entity.Professor;
import org.example.grade_app.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository ProfessorRepository;

    @Transactional(readOnly = true)
    public List<ProfessorDto> getAllProfessors() {
        return ProfessorRepository.findAll()
                .stream()
                .map(DtoMapper::toProfessorDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProfessorDto getProfessorById(Integer id) {
        return ProfessorRepository.findById(id)
                .map(DtoMapper::toProfessorDto)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
    }

    public List<Professor> getAllProfessorsAdmin() {
        return ProfessorRepository.findAll();
    }

    public Professor getProfessorByIdAdmin(Integer id) {
        return ProfessorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
    }
}
