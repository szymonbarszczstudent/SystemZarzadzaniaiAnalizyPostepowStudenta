package org.example.grade_app.controller;

import jakarta.servlet.http.HttpSession;
import org.example.grade_app.dto.AuthUserResponse;
import org.example.grade_app.dto.LoginRequest;
import org.example.grade_app.dto.RegisterRequest;
import org.example.grade_app.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        authService.register(request);
    }

    @PostMapping("/login")
    public AuthUserResponse login(
            @RequestBody LoginRequest request,
            HttpSession session
    ) {
        return authService.login(request, session);
    }

    @GetMapping("/me")
    public AuthUserResponse me(HttpSession session) {
        return authService.me(session);
    }

    @PostMapping("/logout")
    public void logout(HttpSession session) {
        authService.logout(session);
    }
}
