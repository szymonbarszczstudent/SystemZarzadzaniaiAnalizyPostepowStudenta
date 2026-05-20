package org.example.grade_app.service;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.dto.DtoMapper;
import org.example.grade_app.dto.StudentDto;
import org.example.grade_app.entity.Student;
import org.example.grade_app.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository StudentRepository;
    @Transactional(readOnly = true)
    public List<StudentDto> getAllStudents() {
        return StudentRepository.findAll()
                .stream()
                .map(DtoMapper::toStudentDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public StudentDto getStudentById(Integer id) {
        return StudentRepository.findById(id)
                .map(DtoMapper::toStudentDto)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
    public List<Student> getAllStudentsAdmin() {
        return StudentRepository.findAll();
    }

    public Student getStudentByIdAdmin(Integer id) {
        return StudentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
    @Transactional(readOnly = true)
    public StudentDto getStudentByUserId(Integer userId) {
        return StudentRepository.findByUsers_Id(userId)
                .map(DtoMapper::toStudentDto)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
    @Transactional(readOnly = true)
    public List<StudentDto> getStudentsForProfessorUserId(Integer userId) {
        return StudentRepository.findStudentsForProfessor(userId)
                .stream()
                .map(DtoMapper::toStudentDto)
                .toList();
    }
}
