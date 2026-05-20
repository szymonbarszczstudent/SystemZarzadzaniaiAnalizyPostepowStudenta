package org.example.grade_app.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.grade_app.dto.StudentDto;
import org.example.grade_app.entity.Student;
import org.example.grade_app.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

    @RestController
    @RequestMapping("/api/students")
    @RequiredArgsConstructor
    public class StudentController {

        private final StudentService studentService;

        @GetMapping
        public List<StudentDto> getAllStudents(HttpSession session) {
            Integer userId = (Integer) session.getAttribute("userId");
            String role = (String) session.getAttribute("role");

            if (userId == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
            }

            if ("PROFESSOR".equals(role)) {
                return studentService.getStudentsForProfessorUserId(userId);
            }

            if ("STUDENT".equals(role)) {
                return List.of(studentService.getStudentByUserId(userId));
            }

            return studentService.getAllStudents(); // ADMIN
        }

        @GetMapping("/{id}")
        public StudentDto getStudentById(@PathVariable Integer id) {
            return studentService.getStudentById(id);
        }


        @GetMapping("/me")
        public StudentDto getCurrentStudent(HttpSession session) {
            Integer userId = (Integer) session.getAttribute("userId");
            String role = (String) session.getAttribute("role");

            if (userId == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
            }

            if (!"STUDENT".equals(role)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only students can access this endpoint");
            }

            return studentService.getStudentByUserId(userId);
        }
    }
