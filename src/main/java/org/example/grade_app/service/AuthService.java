package org.example.grade_app.service;

import jakarta.servlet.http.HttpSession;
import org.example.grade_app.dto.AuthUserResponse;
import org.example.grade_app.dto.LoginRequest;
import org.example.grade_app.dto.RegisterRequest;
import org.example.grade_app.entity.Professor;
import org.example.grade_app.entity.Student;
import org.example.grade_app.entity.User;
import org.example.grade_app.repository.ProfessorRepository;
import org.example.grade_app.repository.StudentRepository;
import org.example.grade_app.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            StudentRepository studentRepository,
            ProfessorRepository professorRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(RegisterRequest request) {
        if (request.email() == null || request.email().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");
        }

        if (request.password() == null || request.password().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is required");
        }

        if (!request.password().equals(request.confirm_password())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords don't match.");
        }

        if (userRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        User user = new User();
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setRole("STUDENT");

        User savedUser = userRepository.save(user);

        Student student = new Student();
        student.setUsers(savedUser);
        student.setStudentNumber("S" + String.format("%05d", savedUser.getId()));
        student.setFirstName("Nowy");
        student.setLastName("Student");
        student.setStudyYear((byte) 1);
        student.setProgramName("Informatyka");

        studentRepository.save(student);
    }

    public AuthUserResponse login(LoginRequest request, HttpSession session) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid email or password"
                ));

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid email or password"
            );
        }

        AuthUserResponse response = buildAuthUserResponse(user);

        session.setAttribute("userId", user.getId());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("role", user.getRole());
        session.setAttribute("firstName", response.firstName());
        session.setAttribute("lastName", response.lastName());

        return response;
    }

    public AuthUserResponse me(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            return new AuthUserResponse(false, null, null, null, null, null);
        }

        return new AuthUserResponse(
                true,
                userId,
                (String) session.getAttribute("email"),
                (String) session.getAttribute("role"),
                (String) session.getAttribute("firstName"),
                (String) session.getAttribute("lastName")
        );
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private AuthUserResponse buildAuthUserResponse(User user) {
        String firstName = null;
        String lastName = null;

        if ("STUDENT".equals(user.getRole())) {
            Student student = studentRepository.findByUsers_Id(user.getId()).orElse(null);

            if (student != null) {
                firstName = student.getFirstName();
                lastName = student.getLastName();
            }
        }

        if ("PROFESSOR".equals(user.getRole())) {
            Professor professor = professorRepository.findByUsers_Id(user.getId()).orElse(null);

            if (professor != null) {
                firstName = professor.getFirstName();
                lastName = professor.getLastName();
            }
        }

        if ("ADMIN".equals(user.getRole())) {
            firstName = "Administrator";
            lastName = "";
        }

        return new AuthUserResponse(
                true,
                user.getId(),
                user.getEmail(),
                user.getRole(),
                firstName,
                lastName
        );
    }
}