package com.williamfeliciano.sba_grocerystore.services;


import com.williamfeliciano.sba_grocerystore.dtos.LoginRequestDto;
import com.williamfeliciano.sba_grocerystore.dtos.LoginResponseDto;
import com.williamfeliciano.sba_grocerystore.dtos.RegistrationRequestDto;
import com.williamfeliciano.sba_grocerystore.dtos.RegistrationResponseDto;
import com.williamfeliciano.sba_grocerystore.entities.AppUser;
import com.williamfeliciano.sba_grocerystore.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegistrationResponseDto register(RegistrationRequestDto request) {
        var user  = AppUser.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(Arrays.toString(request.getPassword())).toCharArray())
                .build();
        userRepository.save(user);
        UserDetails userDetails = User.builder()
                .username(user.getUsername())
                .password(Arrays.toString(user.getPassword()))
                .roles("USER")
                .build();
        var jwtToken = jwtService.generateToken(userDetails);
        return RegistrationResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public LoginResponseDto login(LoginRequestDto loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        Arrays.toString(loginRequest.getPassword())
                )
        );
        var user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not registered"));
        UserDetails userDetails = User.builder()
                .username(user.getUsername())
                .password(Arrays.toString(user.getPassword()))
                .roles("USER")
                .build();
        var jwtToken = jwtService.generateToken(userDetails);
        return LoginResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}