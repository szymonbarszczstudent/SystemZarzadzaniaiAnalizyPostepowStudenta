package org.example.grade_app.controller;

import jakarta.servlet.http.HttpSession;
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
    public List<ExamDto> getAllExams(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        if ("STUDENT".equals(role)) {
            return ExamService.getExamsForStudentUserId(userId);
        }

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

