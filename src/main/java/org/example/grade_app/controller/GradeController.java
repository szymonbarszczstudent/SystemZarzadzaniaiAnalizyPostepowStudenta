package org.example.grade_app.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.grade_app.dto.CreateGradeRequest;
import org.example.grade_app.dto.GradeDto;
import org.example.grade_app.service.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @GetMapping
    public List<GradeDto> getAllGrades(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        if ("STUDENT".equals(role)) {
            return gradeService.getGradesForStudentUserId(userId);
        }

        if ("PROFESSOR".equals(role)) {
            return gradeService.getGradesForProfessorUserId(userId);
        }

        return gradeService.getAllGrades(); // ADMIN
    }

    @GetMapping("/{id}")
    public GradeDto getGradeById(@PathVariable Integer id) {
        return gradeService.getGradeById(id);
    }

    @PostMapping
    public GradeDto createGrade(@RequestBody CreateGradeRequest request, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        if (!"PROFESSOR".equals(role)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only professor can add grades");
        }
        System.out.println("GET /api/grades userId=" + userId + ", role=" + role);
        return gradeService.createGrade(request, userId);
    }

}