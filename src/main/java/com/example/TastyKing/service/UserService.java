package com.example.TastyKing.service;

import com.example.TastyKing.dto.request.RegisterRequest;
import com.example.TastyKing.dto.response.UserResponse;
import com.example.TastyKing.entity.RewardPoint;
import com.example.TastyKing.entity.User;
import com.example.TastyKing.enums.Role;
import com.example.TastyKing.exception.AppException;
import com.example.TastyKing.exception.ErrorCode;
import com.example.TastyKing.mapper.UserMapper;
import com.example.TastyKing.repository.RewardPointRepository;
import com.example.TastyKing.repository.UserRepository;
import com.example.TastyKing.util.EmailUtil;
import com.example.TastyKing.util.OtpUtil;
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
    @Autowired
    private RewardPointRepository rewardPointRepository;

    public String createNewUser(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.EMAIL_EXISTED);

        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(request.getEmail(), otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        User user = new User();
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(Role.USER.name());
        user.setOtp(otp);
        user.setGenerateOtpTime(LocalDateTime.now());
        user.setUserName(request.getEmail());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        userRepository.save(user);
        return "To verify this is your email account, we will send a confirmation code to this email. Please check your email to receive the verification code to activate your account";

    }

    public boolean verifyOtp(String email, String otp) {

    User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.EMAIL_NOT_EXISTED));
        if (user.getOtp().equals(otp) &&
                Duration.between(user.getGenerateOtpTime(), LocalDateTime.now()).getSeconds() < 60) {
            user.setActive(true);
            userRepository.save(user);
            RewardPoint rewardPoint = new RewardPoint();
            rewardPoint.setUser(user);
            rewardPoint.setBalance(0.0);
            rewardPointRepository.save(rewardPoint);
            return true;
        }
        return false;
    }
    public String regenerateOtp(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.EMAIL_NOT_EXISTED));
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
