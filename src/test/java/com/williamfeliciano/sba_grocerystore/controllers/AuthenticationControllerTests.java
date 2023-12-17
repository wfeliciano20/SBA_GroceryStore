package com.williamfeliciano.sba_grocerystore.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.williamfeliciano.sba_grocerystore.dtos.LoginRequestDto;
import com.williamfeliciano.sba_grocerystore.dtos.LoginResponseDto;
import com.williamfeliciano.sba_grocerystore.dtos.RegistrationRequestDto;
import com.williamfeliciano.sba_grocerystore.dtos.RegistrationResponseDto;
import com.williamfeliciano.sba_grocerystore.services.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ActiveProfiles("test")
class AuthenticationControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ObjectMapper objectMapper;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
    }
    @Test
    void register_ShouldReturnOk() throws Exception {
        // Arrange
        RegistrationRequestDto request = new RegistrationRequestDto("testuser", "password123".toCharArray());
        RegistrationResponseDto responseDto = new RegistrationResponseDto("generatedToken");

        when(authenticationService.register(request)).thenReturn(responseDto);

        // Act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/register",request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String content = mvcResult.getResponse().getContentAsString();
        RegistrationResponseDto actualResponse = objectMapper.readValue(content, RegistrationResponseDto.class);
        assertThat(actualResponse.getToken()).isEqualTo("generatedToken");
    }



    @Test
    void login_ShouldReturnOk() throws Exception {
        // Arrange
        LoginRequestDto loginRequest = new LoginRequestDto("testuser", "password123".toCharArray());
        LoginResponseDto responseDto = new LoginResponseDto("generatedToken");

        when(authenticationService.login(loginRequest)).thenReturn(responseDto);

        // Act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login",loginRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String content = mvcResult.getResponse().getContentAsString();
        LoginResponseDto actualResponse = objectMapper.readValue(content, LoginResponseDto.class);
        assertThat(actualResponse.getToken()).isEqualTo("generatedToken");
    }
}
