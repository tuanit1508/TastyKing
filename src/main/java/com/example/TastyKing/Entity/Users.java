package com.example.TastyKing.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)

@Table(name = "users")
public class Users {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "UserID")
 int userId;

 @Column(name = "fullname", nullable = false, length = 255)
 String fullName;

 @Column(name = "username", nullable = false, length = 50)
String userName;

 @Column(name = "email", nullable = false, length = 100)
String email;

 @Column(name = "phone", nullable = false, length = 15)
 String phone;

 @Column(name = "password", nullable = false, length = 255)
 String password;

 @Column(name = "role", nullable = false, length = 50)
 String role;

 @Column(name = "active")
 boolean active;

 @Column(name = "otp", length = 10)
 String otp;

 @Column(name = "generateotptime")
 LocalDateTime generateOtpTime;

}
