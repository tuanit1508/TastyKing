package com.example.TastyKing.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor

public class UserResponse {

   private String userName;
   private String fullName;
   private String email;
   private String phone;
   private String role;
}
