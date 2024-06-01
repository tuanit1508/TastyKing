package com.example.TastyKing.Service;

import com.example.TastyKing.Dto.Request.RegisterRequest;
import com.example.TastyKing.Dto.Response.UserResponse;
import com.example.TastyKing.Entity.Users;
import com.example.TastyKing.Enum.Role;
import com.example.TastyKing.Exception.AppException;
import com.example.TastyKing.Exception.ErrorCode;
import com.example.TastyKing.Mapper.UserMapper;
import com.example.TastyKing.Repository.UserRepository;
import com.example.TastyKing.Util.EmailUtil;
import com.example.TastyKing.Util.OtpUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;


@Service
public class UserService {
    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    public String createNewUser(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.EMAIL_EXISTED);

        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(request.getEmail(), otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        Users user = new Users();
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(Role.USER.name());
        user.setOtp(otp);
        user.setGenerateOtpTime(LocalDateTime.now());
        user.setUserName(request.getUserName());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        userRepository.save(user);
        return "To verify this is your email account, we will send a confirmation code to this email. Please check your email to receive the verification code to activate your account";

    }

    public boolean verifyOtp(String email, String otp) {

    Users user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.EMAIL_NOT_EXISTED));
        if (user.getOtp().equals(otp) &&
                Duration.between(user.getGenerateOtpTime(), LocalDateTime.now()).getSeconds() < 60) {
            user.setActive(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }
    public String regenerateOtp(String email) {
        Users user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.EMAIL_NOT_EXISTED));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        user.setOtp(otp);
        user.setGenerateOtpTime(LocalDateTime.now());
        userRepository.save(user);
        return "Email sent...Please check email to verify account within 1 minutes";
    }
    public UserResponse getUserByEmail(String email) {
        return userMapper.toUserResponse(userRepository.findByEmail(email).orElseThrow(() ->
                new AppException(ErrorCode.EMAIL_NOT_EXISTED)));

    }

}
