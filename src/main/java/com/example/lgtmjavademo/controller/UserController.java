package com.example.lgtmjavademo.controller;


import com.example.lgtmjavademo.dto.request.UserRequest;
import com.example.lgtmjavademo.dto.response.UserResponse;
import com.example.lgtmjavademo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        log.info("GET {} request received", getRequestPath());
        UserResponse response = userService.getUser(id);
        log.info("GET {} request completed", getRequestPath());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        log.info("POST {} request received - username: {}", getRequestPath(), userRequest.getUsername());
        UserResponse response = userService.createUser(userRequest);
        log.info("POST {} request completed - username: {}", getRequestPath(), response.getUsername());
        return ResponseEntity.ok(response);
    }

    private String getRequestPath() {
        return ServletUriComponentsBuilder.fromCurrentRequest().build().getPath();
    }
}
