package org.example.grade_app.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.grade_app.dto.SubjectDto;
import org.example.grade_app.entity.Subject;
import org.example.grade_app.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService SubjectService;

    @GetMapping
    public List<SubjectDto> getAllSubjects(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        if ("STUDENT".equals(role)) {
            return SubjectService.getSubjectsForStudentUserId(userId);
        }

        if ("PROFESSOR".equals(role)) {
            return SubjectService.getSubjectsForProfessorUserId(userId);
        }

        return SubjectService.getAllSubjects(); // ADMIN
    }

    @GetMapping("/{id}")
    public SubjectDto getSubjectById(@PathVariable Integer id) {
        return SubjectService.getSubjectById(id);
    }
}
