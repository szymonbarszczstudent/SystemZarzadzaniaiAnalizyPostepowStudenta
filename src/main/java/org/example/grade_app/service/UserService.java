package org.example.grade_app.service;

import lombok.RequiredArgsConstructor;
import org.example.grade_app.dto.DtoMapper;
import org.example.grade_app.dto.UserDto;
import org.example.grade_app.entity.User;
import org.example.grade_app.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository UserRepository;

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return UserRepository.findAll()
                .stream()
                .map(DtoMapper::toUserDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserDto getUserById(Integer id) {
        return UserRepository.findById(id)
                .map(DtoMapper::toUserDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsersAdmin() {
        return UserRepository.findAll();
    }

    public User getUserByIdAdmin(Integer id) {
        return UserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}

