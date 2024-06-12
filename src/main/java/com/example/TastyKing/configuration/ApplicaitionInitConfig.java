package com.example.TastyKing.configuration;


import com.example.TastyKing.entity.User;
import com.example.TastyKing.enums.Role;
import com.example.TastyKing.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class ApplicaitionInitConfig {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner (UserRepository userRepository){
        return args ->{
            if(userRepository.findByUserName("admin").isEmpty()){
                User user = User.builder()
                        .userName("admin")
                        .email("admin")
                        .password(passwordEncoder.encode("admin"))
                        .role(Role.ADMIN.name())
                        .active(true)
                        .build();
                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it.");
            }
        };
    }
}
