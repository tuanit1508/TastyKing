package com.example.TastyKing.mapper;

import com.example.TastyKing.dto.request.RegisterRequest;
import com.example.TastyKing.dto.response.UserResponse;
import com.example.TastyKing.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RegisterRequest request);
    UserResponse toUserResponse(User user);
}
