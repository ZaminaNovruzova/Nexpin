package com.finalproject.nexpin.controller;

import com.finalproject.nexpin.config.JwtGenerator;
import com.finalproject.nexpin.dto.AuthenticationResponse;
import com.finalproject.nexpin.dto.LoginRequest;
import com.finalproject.nexpin.dto.RegisterRequest;
import com.finalproject.nexpin.entity.Role;
import com.finalproject.nexpin.entity.User;
import com.finalproject.nexpin.repository.RoleRepository;
import com.finalproject.nexpin.repository.UserRepository;
import com.finalproject.nexpin.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegisterLoginController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUserName(registerRequest.getUsername())) {

            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);

        }
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        Role roles = roleRepository.findByName("USER").orElseThrow();
        user.setRoles(Collections.singletonList(roles));
        userRepository.save(user);
        return new ResponseEntity<>("User registered success", HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest){
      Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                  loginRequest.getUsername(),loginRequest.getPassword()
          ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthenticationResponse(token),HttpStatus.OK);

    }
}

