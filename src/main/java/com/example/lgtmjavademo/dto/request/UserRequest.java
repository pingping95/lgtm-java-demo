package com.example.lgtmjavademo.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "username is required")
    private String username;

    @NotNull(message = "age is required")
    @Min(value = 0, message = "age must be greater or equal than 0")
    private Integer age;
}