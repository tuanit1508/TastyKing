package com.example.TastyKing.Service;

import com.example.TastyKing.Dto.Request.AuthenticationRequest;
import com.example.TastyKing.Dto.Response.AuthenticationResponse;
import com.example.TastyKing.Entity.Users;
import com.example.TastyKing.Exception.AppException;
import com.example.TastyKing.Exception.ErrorCode;
import com.example.TastyKing.Repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    protected static final String signature ="8UJijdAgFeqP7UReJU3e+cCQcVPnW1cK/JxPC0+CUnoo5h36rd3QicT7nPBhbtBW";
    @Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest){
        Users user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(
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

    private String generateToken(Users user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issuer("TastyKing.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", buildScope(user)).build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
            try {
                jwsObject.sign(new MACSigner(signature.getBytes()));
                return jwsObject.serialize();
            } catch (JOSEException e) {
                throw new RuntimeException(e);
            }
    }
    private String buildScope(Users user) {
        if (!StringUtils.isEmpty(user.getRole())) {
            return user.getRole();
        }
        return "";
    }
}
