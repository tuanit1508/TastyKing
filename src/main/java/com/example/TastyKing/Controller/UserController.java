package com.example.TastyKing.Controller;


import com.example.TastyKing.Dto.Request.OtpRequest;
import com.example.TastyKing.Dto.Request.RegisterRequest;
import com.example.TastyKing.Dto.Response.ApiResponse;
import com.example.TastyKing.Dto.Response.UserResponse;
import com.example.TastyKing.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
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
    public ResponseEntity<String> regenerateOtp(@RequestParam("email") String email) {
        return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
    }

}
