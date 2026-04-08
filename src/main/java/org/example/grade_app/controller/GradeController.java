package org.example.grade_app.controller;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.dto.CreateGradeRequest;
import org.example.grade_app.dto.GradeDto;
import org.example.grade_app.entity.Grade;
import org.example.grade_app.service.GradeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService GradeService;


    @GetMapping
    public List<GradeDto> getAllGrades() {
        return GradeService.getAllGrades();
    }

    @GetMapping("/{id}")
    public GradeDto getGradeById(@PathVariable Integer id) {
        return GradeService.getGradeById(id);
    }

    @PostMapping
    public GradeDto createGrade(@RequestBody CreateGradeRequest request) {
        return GradeService.createGrade(request);
    }
}

