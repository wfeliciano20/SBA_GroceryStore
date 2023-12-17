package com.williamfeliciano.sba_grocerystore.services;

import com.williamfeliciano.sba_grocerystore.dtos.LoginRequestDto;
import com.williamfeliciano.sba_grocerystore.dtos.LoginResponseDto;
import com.williamfeliciano.sba_grocerystore.dtos.RegistrationRequestDto;
import com.williamfeliciano.sba_grocerystore.dtos.RegistrationResponseDto;
import com.williamfeliciano.sba_grocerystore.entities.AppUser;
import com.williamfeliciano.sba_grocerystore.repositories.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private AppUserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void register_ShouldGenerateTokenForNewUser() {
        // Arrange
        RegistrationRequestDto request = new RegistrationRequestDto("testuser", "password123".toCharArray());
        AppUser user = AppUser.builder().username(request.getUsername()).password("encodedPassword".toCharArray()).build();

        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");
        when(userRepository.save((AppUser) any(AppUser.class))).thenReturn(user);
        when(jwtService.generateToken((UserDetails) any(UserDetails.class))).thenReturn("generatedToken");

        // Act
        RegistrationResponseDto response = authenticationService.register(request);

        // Assert
        verify(userRepository, times(1)).save((AppUser) any(AppUser.class));
        verify(jwtService, times(1)).generateToken((UserDetails) any(UserDetails.class));

        assertThat(response.getToken()).isEqualTo("generatedToken");
    }

    @Test
    void login_ShouldGenerateTokenForExistingUser() {
        // Arrange
        LoginRequestDto loginRequest = new LoginRequestDto("testuser", "password123".toCharArray());
        AppUser user = AppUser.builder().username(loginRequest.getUsername()).password("encodedPassword".toCharArray()).build();

        when(userRepository.findByUsername(loginRequest.getUsername())).thenReturn(Optional.of(user));
        when(jwtService.generateToken((UserDetails) any(UserDetails.class))).thenReturn("generatedToken");

        // Act
        LoginResponseDto response = authenticationService.login(loginRequest);

        // Assert
        verify(authenticationManager, times(1)).authenticate((Authentication) any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService, times(1)).generateToken((UserDetails) any(UserDetails.class));

        assertThat(response.getToken()).isEqualTo("generatedToken");
    }
}
