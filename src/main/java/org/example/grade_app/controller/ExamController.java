package org.example.grade_app.controller;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.entity.Exam;
import org.example.grade_app.service.ExamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService ExamService;

    @GetMapping
    public List<Exam> getAllExams() {
        return ExamService.getAllExams();
    }

    @GetMapping("/{id}")
    public Exam getExamById(@PathVariable Integer id) {
        return ExamService.getExamById(id);
    }
}

