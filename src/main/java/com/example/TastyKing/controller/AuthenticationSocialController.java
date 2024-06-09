package com.example.TastyKing.controller;

import com.example.TastyKing.dto.request.AuthenticationRequest;
import com.example.TastyKing.dto.response.ApiResponse;
import com.example.TastyKing.dto.response.AuthenticationResponse;
import com.example.TastyKing.service.Oauth2UserService;
import com.example.TastyKing.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth-social")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationSocialController {

    private final JwtUtil jwtTokenUtil;
    @GetMapping("/login")
    public ResponseEntity<Void> getJwtToken(@AuthenticationPrincipal OAuth2User oauth2User, HttpServletResponse response) throws IOException {
        if (oauth2User == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = jwtTokenUtil.generateToken(oauth2User);
        response.sendRedirect("http://localhost:8080/TastyKing/home?token=" + token); // Redirect to home page with token
        return ResponseEntity.ok().build();
    }
}
