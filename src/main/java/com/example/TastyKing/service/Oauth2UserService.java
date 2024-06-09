package com.example.TastyKing.service;

import com.example.TastyKing.entity.Users;
import com.example.TastyKing.enums.Role;
import com.example.TastyKing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class Oauth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
    @Autowired
    private UserRepository userRepository;



    public Users processOauth2User(OAuth2User oAuth2User, String provider) {
        String email = oAuth2User.getAttribute("email");
        Optional<Users> userExisting = userRepository.findByEmail(email);
        Users users;
        if (userExisting.isPresent()) {
            users = userExisting.get();
        } else {
            users = new Users();
            users.setUserName(email);
            users.setEmail(email);
            String fullName = oAuth2User.getAttribute("name");
            if (fullName == null) {
                fullName = "Default Name"; // or handle it appropriately
            }
            users.setFullName(fullName);
            users.setProvider(provider);
            users.setProviderId(oAuth2User.getName());
            users.setRole(Role.USER.name());
            users.setActive(true);
            userRepository.save(users);
        }
        return users;
    }
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = new DefaultOAuth2User(
                Collections.singletonList(() -> "ROLE_USER"),
                userRequest.getAdditionalParameters(),
                "name"
        );

        return (OAuth2User) processOauth2User(oAuth2User, "google");
    }
}
