package com.example.TastyKing.controller;


import com.example.TastyKing.dto.request.OtpRequest;
import com.example.TastyKing.dto.request.RegisterRequest;
import com.example.TastyKing.dto.response.ApiResponse;
import com.example.TastyKing.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody @Valid RegisterRequest request){
            return ApiResponse.<String>builder()
                    .result(userService.createNewUser(request))
                    .build();
    }
    @PutMapping("/verify-account")
    public ApiResponse<String> verifyAccount(@RequestBody OtpRequest otpRequest){
        boolean isVerified = userService.verifyOtp(otpRequest.getEmail(), otpRequest.getOtp());
        if (isVerified) {
//            return new ResponseEntity<>("OTP verified successfully.", HttpStatus.OK);
            return ApiResponse.<String>builder()
                    .result("OTP verified successfully")
                    .build();
        } else {
           return ApiResponse.<String>builder()
                   .result("Invalid OTP")
                   .build();
        }
    }
    @PutMapping("/regenerate-otp")
    public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
        System.out.println("Email received: " + email); // Debug log
        return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
    }

}
