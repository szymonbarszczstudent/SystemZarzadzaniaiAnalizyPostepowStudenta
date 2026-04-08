package org.example.grade_app.dto;

import org.example.grade_app.entity.Exam;
import org.example.grade_app.entity.Grade;
import org.example.grade_app.entity.Professor;
import org.example.grade_app.entity.Student;
import org.example.grade_app.entity.Subject;
import org.example.grade_app.entity.User;

public final class DtoMapper {

    private DtoMapper() {
    }

    public static StudentDto toStudentDto(Student s) {
        return new StudentDto(
                s.getStudentNumber(),
                s.getFirstName(),
                s.getLastName(),
                s.getStudyYear(),
                s.getProgramName()
        );
    }

    public static UserDto toUserDto(User u) {
        return new UserDto(
                u.getEmail(),
                u.getRole(),
                u.getCreatedAt()
        );
    }

    public static ProfessorDto toProfessorDto(Professor p) {
        return new ProfessorDto(
                p.getTitle(),
                p.getFirstName(),
                p.getLastName()
        );
    }

    public static SubjectDto toSubjectDto(Subject s) {
        return new SubjectDto(
                s.getCode(),
                s.getName(),
                s.getEcts(),
                s.getDescription()
        );
    }

    public static ExamDto toExamDto(Exam e) {
        return new ExamDto(
                e.getEnrollment().getStudent().getStudentNumber(),
                e.getEnrollment().getSubject().getCode(),
                e.getEnrollment().getSubject().getName(),
                e.getProfessor().getLastName(),
                e.getAttemptNumber(),
                e.getExamDate(),
                e.getStatus(),
                e.getGradeValue(),
                e.getComment(),
                e.getRecordedAt()
        );
    }

    public static GradeDto toGradeDto(Grade g) {
        return new GradeDto(
                g.getEnrollment().getStudent().getStudentNumber(),
                g.getEnrollment().getSubject().getCode(),
                g.getEnrollment().getSubject().getName(),
                g.getProfessor().getLastName(),
                g.getCategory(),
                g.getGradeValue(),
                g.getWeight(),
                g.getComment(),
                g.getGradedAt()
        );
    }

}
