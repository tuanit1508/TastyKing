package com.example.TastyKing.Dto.Request;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    @Size(min = 5, message = "USERNAME_INVALID")
    String userName;
    String fullName;
    String email;
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    String phone;

}
