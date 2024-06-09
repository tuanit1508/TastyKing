package com.example.TastyKing.controller;


import com.example.TastyKing.dto.request.OtpRequest;
import com.example.TastyKing.dto.request.RegisterRequest;
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
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest request){
            return new ResponseEntity<>(userService.createNewUser(request),  HttpStatus.OK);
    }
    @PutMapping("/verify-account")
    public ResponseEntity<String> verifyAccount(@RequestBody OtpRequest otpRequest){
        boolean isVerified = userService.verifyOtp(otpRequest.getEmail(), otpRequest.getOtp());
        if (isVerified) {
            return new ResponseEntity<>("OTP verified successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid OTP.", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/regenerate-otp")
    public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
        System.out.println("Email received: " + email); // Debug log
        return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
    }

}
