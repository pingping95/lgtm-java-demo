package com.example.lgtmjavademo.service;

import com.example.lgtmjavademo.domain.user.mapper.UserMapper;
import com.example.lgtmjavademo.domain.user.repository.UserRepository;
import com.example.lgtmjavademo.dto.request.UserRequest;
import com.example.lgtmjavademo.dto.response.UserResponse;
import com.example.lgtmjavademo.entity.User;
import com.example.lgtmjavademo.global.error.exception.UserNotFoundException;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        return userMapper.toResponse(user);
    }

    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        validateUsername(userRequest.getUsername());

        User user = userMapper.toEntity(userRequest);
        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    private void validateUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists: " + username);
        }
    }
}
