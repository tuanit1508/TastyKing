package com.example.TastyKing.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {


    String fullName;
    @Email(message = "EMAIL_VALID")
    String email;
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    @Size(min = 10, message = "PHONE_INVALID")
     String phone;

}
