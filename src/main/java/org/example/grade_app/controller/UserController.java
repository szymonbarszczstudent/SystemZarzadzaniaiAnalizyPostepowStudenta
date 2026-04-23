package org.example.grade_app.controller;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.dto.UserDto;
import org.example.grade_app.entity.User;
import org.example.grade_app.repository.UserRepository;
import org.example.grade_app.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    // GET ALL
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    // DELETE
    public void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }
}