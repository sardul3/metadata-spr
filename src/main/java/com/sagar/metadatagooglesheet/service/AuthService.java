package com.sagar.metadatagooglesheet.service;

import com.sagar.metadatagooglesheet.dto.RegisterRequest;
import com.sagar.metadatagooglesheet.model.Developer;
import com.sagar.metadatagooglesheet.model.User;
import com.sagar.metadatagooglesheet.repository.DeveloperRepository;
import com.sagar.metadatagooglesheet.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final DeveloperRepository developerRepository;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(true);
        user.setAvatar(Integer.toString((int) Math.floor(Math.random()*10+1)));

        developerRepository.save(new Developer(user.getUsername(), user.getEmail(), "Developer", user.getAvatar()));



        userRepository.save(user);
    }
}
