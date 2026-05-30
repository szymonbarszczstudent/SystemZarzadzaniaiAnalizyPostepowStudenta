package org.example.grade_app.service;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.dto.DtoMapper;
import org.example.grade_app.dto.SubjectDto;
import org.example.grade_app.entity.Subject;
import org.example.grade_app.repository.EnrollmentRepository;
import org.example.grade_app.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository SubjectRepository;
    private final EnrollmentRepository EnrollmentRepository;

    @Transactional(readOnly = true)
    public List<SubjectDto> getAllSubjects() {
        return SubjectRepository.findAll()
                .stream()
                .map(DtoMapper::toSubjectDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public SubjectDto getSubjectById(Integer id) {
        return SubjectRepository.findById(id)
                .map(DtoMapper::toSubjectDto)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
    }

    public List<Subject> getAllSubjectsAdmin() {
        return SubjectRepository.findAll();
    }

    public Subject getSubjectByIdAdmin(Integer id) {
        return SubjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
    }
    @Transactional(readOnly = true)
    public List<SubjectDto> getSubjectsForStudentUserId(Integer userId) {
        return SubjectRepository.findSubjectsForStudentUserId(userId)
                .stream()
                .map(DtoMapper::toSubjectDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<SubjectDto> getSubjectsForProfessorUserId(Integer userId) {
        return SubjectRepository.findSubjectsForProfessor(userId)
                .stream()
                .map(DtoMapper::toSubjectDto)
                .toList();
    }
}
