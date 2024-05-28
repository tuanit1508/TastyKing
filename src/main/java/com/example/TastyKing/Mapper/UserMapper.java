package com.example.TastyKing.Mapper;

import com.example.TastyKing.Dto.Request.RegisterRequest;
import com.example.TastyKing.Dto.Response.UserResponse;
import com.example.TastyKing.Entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users toUser(RegisterRequest request);
    UserResponse toUserResponse(Users user);
}
