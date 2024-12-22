package com.example.lgtmjavademo.domain.user.mapper;

import com.example.lgtmjavademo.dto.request.UserRequest;
import com.example.lgtmjavademo.dto.response.UserResponse;
import com.example.lgtmjavademo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // userRequest를 user로 변환
    User toEntity(UserRequest request);

    // user를 userResponse로 변환
    UserResponse toResponse(User user);
}