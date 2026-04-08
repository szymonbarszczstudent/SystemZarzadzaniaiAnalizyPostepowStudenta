package org.example.grade_app.controller;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.dto.SubjectDto;
import org.example.grade_app.entity.Subject;
import org.example.grade_app.service.SubjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService SubjectService;

    @GetMapping
    public List<SubjectDto> getAllSubjects() {
        return SubjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public SubjectDto getSubjectById(@PathVariable Integer id) {
        return SubjectService.getSubjectById(id);
    }
}
