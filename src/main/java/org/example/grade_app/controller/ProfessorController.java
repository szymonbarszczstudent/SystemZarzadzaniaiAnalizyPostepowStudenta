package org.example.grade_app.controller;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.entity.Professor;
import org.example.grade_app.service.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professors")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping
    public List<Professor> getAllProfessors() {
        return professorService.getAllProfessors();
    }

    @GetMapping("/{id}")
    public Professor getProfessorById(@PathVariable Integer id) {
        return professorService.getProfessorById(id);
    }
}