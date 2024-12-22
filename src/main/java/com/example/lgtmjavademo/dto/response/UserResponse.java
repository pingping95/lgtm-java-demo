package com.example.lgtmjavademo.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserResponse {
    private final Long id;
    private final String username;
    private final int age;
    private final LocalDate createdAt;
    private final LocalDate updatedAt;
}