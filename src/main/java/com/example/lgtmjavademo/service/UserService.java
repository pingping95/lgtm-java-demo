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

    // 의존성 주입
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // 사용자 조회 메서드
    public UserResponse getUser(Long id) {
        // User 객체를 id로 조회
        User user = userRepository.findById(id)
                // 존재하지 않는 경우 예외 처리
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        // UserResponse로 변환하여 반환
        return userMapper.toResponse(user);
    }

    // 사용자 생성 메서드
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        // 사용자명 중복 검사
        validateUsername(userRequest.getUsername());

        // UserRequest를 User로 변환
        User user = userMapper.toEntity(userRequest);
        // User 저장
        User savedUser = userRepository.save(user);

        // UserResponse로 변환하여 반환
        return userMapper.toResponse(savedUser);
    }

    // 사용자명 중복 검사 메서드
    private void validateUsername(String username) {
        // 사용자명이 이미 존재하는 경우 예외 처리
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists: " + username);
        }
    }
}
