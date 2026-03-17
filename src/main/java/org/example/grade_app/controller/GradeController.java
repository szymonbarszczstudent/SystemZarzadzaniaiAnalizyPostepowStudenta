package org.example.grade_app.controller;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.entity.Grade;
import org.example.grade_app.service.GradeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService GradeService;


    @GetMapping
    public List<Grade> getAllGrades() {
        return GradeService.getAllGrades();
    }

    @GetMapping("/{id}")
    public Grade getGradeById(@PathVariable Integer id) {
        return GradeService.getGradeById(id);
    }
}

