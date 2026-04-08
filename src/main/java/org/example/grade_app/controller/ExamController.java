package org.example.grade_app.controller;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.dto.CreateExamRequest;
import org.example.grade_app.dto.ExamDto;
import org.example.grade_app.entity.Exam;
import org.example.grade_app.service.ExamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService ExamService;

    @GetMapping
    public List<ExamDto> getAllExams() {
        return ExamService.getAllExams();
    }

    @GetMapping("/{id}")
    public ExamDto getExamById(@PathVariable Integer id) {
        return ExamService.getExamById(id);
    }

    @PostMapping
    public ExamDto createExam(@RequestBody CreateExamRequest request) {
        return ExamService.createExam(request);
    }
}

