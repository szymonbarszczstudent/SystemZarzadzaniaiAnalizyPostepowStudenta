package org.example.grade_app.service;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.entity.Student;
import org.example.grade_app.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository StudentRepository;

    public List<Student> getAllStudents() {
        return StudentRepository.findAll();
    }

    public Student getStudentById(Integer id) {
        return StudentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
}
