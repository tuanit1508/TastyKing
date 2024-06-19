package com.example.TastyKing.dto.request;


import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor

public class UpdateUserRequest {
   @Size(min = 5, message = "USERNAME_INVALID")
   private String userName;
   private String fullName;
   @Size(min = 10, message = "PHONE_INVALID")
   private String phone;
}
