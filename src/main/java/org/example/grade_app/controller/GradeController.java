package org.example.grade_app.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.grade_app.dto.CreateGradeRequest;
import org.example.grade_app.dto.GradeDto;
import org.example.grade_app.entity.Grade;
import org.example.grade_app.service.GradeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService GradeService;

    @GetMapping
    public List<GradeDto> getAllGrades(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        if ("STUDENT".equals(role)) {
            return GradeService.getGradesForStudentUserId(userId);
        }

        if ("PROFESSOR".equals(role)) {
            return GradeService.getGradesForProfessorUserId(userId);
        }

        return GradeService.getAllGrades(); // ADMIN
    }

    @GetMapping("/{id}")
    public GradeDto getGradeById(@PathVariable Integer id) {
        return GradeService.getGradeById(id);
    }

    @PostMapping
    public GradeDto createGrade(@RequestBody CreateGradeRequest request, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        if (!"PROFESSOR".equals(role) && !"ADMIN".equals(role)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only professor or admin can create grades");
        }

        return GradeService.createGrade(request, userId);
    }
}

