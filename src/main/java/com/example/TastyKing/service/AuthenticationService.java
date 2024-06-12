package com.example.TastyKing.service;

import com.example.TastyKing.dto.request.AuthenticationRequest;
import com.example.TastyKing.dto.response.AuthenticationResponse;
import com.example.TastyKing.entity.User;
import com.example.TastyKing.exception.AppException;
import com.example.TastyKing.exception.ErrorCode;
import com.example.TastyKing.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor

public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    protected static final String signature ="OG3aRIYXHjOowyfI2MOHbl8xSjoF/B/XwkK6b276SfXAhL3KbizWWuT8LB1YUVvh";
    @Autowired
    private PasswordEncoder passwordEncoder;
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest){
        User user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(
                () -> new AppException(ErrorCode.EMAIL_NOT_EXISTED)

        );
        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
        if (!authenticated)
            throw new AppException(ErrorCode.LOGIN_FAILED);
        var token =generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true).build();

    }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issuer("TastyKing.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope",buildScope(user)).build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
            try {
                jwsObject.sign(new MACSigner(signature.getBytes()));
                return jwsObject.serialize();
            } catch (JOSEException e) {
                throw new RuntimeException(e);
            }
    }
    public String buildScope(User user) {
        if (!StringUtils.isEmpty(user.getRole())) {
            return "ROLE_" + user.getRole();
        }
        return "";

    }
}
