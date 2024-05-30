package com.example.TastyKing.Controller;

import com.example.TastyKing.Dto.Request.AuthenticationRequest;
import com.example.TastyKing.Dto.Response.ApiResponse;
import com.example.TastyKing.Dto.Response.AuthenticationResponse;
import com.example.TastyKing.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private AuthenticationService authenticationService;
        @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest){
            var result = authenticationService.login(authenticationRequest);
            return ApiResponse.<AuthenticationResponse>builder()
                    .result(result)
                    .build();

        }
}
