package com.example.TastyKing.mapper;

import com.example.TastyKing.dto.request.RegisterRequest;
import com.example.TastyKing.dto.request.UpdateUserRequest;
import com.example.TastyKing.dto.response.UserResponse;
import com.example.TastyKing.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RegisterRequest request);
    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UpdateUserRequest userUpdateRequest);

}
