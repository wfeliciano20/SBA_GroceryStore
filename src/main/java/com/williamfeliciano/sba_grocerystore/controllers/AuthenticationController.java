package com.williamfeliciano.sba_grocerystore.controllers;

import com.williamfeliciano.sba_grocerystore.dtos.LoginRequestDto;
import com.williamfeliciano.sba_grocerystore.dtos.LoginResponseDto;
import com.williamfeliciano.sba_grocerystore.dtos.RegistrationRequestDto;
import com.williamfeliciano.sba_grocerystore.dtos.RegistrationResponseDto;
import com.williamfeliciano.sba_grocerystore.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDto> register(
            @RequestBody RegistrationRequestDto request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody LoginRequestDto loginRequest
    ){
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }


}