package org.example.grade_app.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.grade_app.dto.StudentDto;
import org.example.grade_app.entity.Student;
import org.example.grade_app.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

    @RestController
    @RequestMapping("/api/students")
    @RequiredArgsConstructor
    public class StudentController {

        private final StudentService studentService;

        @GetMapping
        public List<StudentDto> getAllStudents() {
            return studentService.getAllStudents();
        }

        @GetMapping("/{id}")
        public StudentDto getStudentById(@PathVariable Integer id) {
            return studentService.getStudentById(id);
        }


        @GetMapping("/me")
        public StudentDto getCurrentStudent(HttpSession session) {
            Integer userId = (Integer) session.getAttribute("userId");

            if (userId == null) {
                throw new RuntimeException("Not logged in");
            }

            return studentService.getStudentByUserId(userId);
        }
    }
