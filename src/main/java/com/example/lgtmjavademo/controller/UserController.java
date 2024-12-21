package com.example.lgtmjavademo.controller;


import com.example.lgtmjavademo.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/user")
    public UserDto getUser(@RequestParam String name, @RequestParam int age) {
        return new UserDto(name, age);
    }
}
