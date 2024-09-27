package com.application.controller;

import com.application.dto.LoginRequest;
import com.application.dto.SignupRequest;
import com.application.jwt.JwtService;
import com.application.model.Employee;
import com.application.services.ApiUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AuthController authController;

    @Mock
    private ApiUserService userService;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void testSignup() throws Exception {
        SignupRequest signupRequest = new SignupRequest("testuser", "testuser@example.com", "password");
        String requestJson = objectMapper.writeValueAsString(signupRequest);

        Employee newUser = new Employee();
        newUser.setUserName("testuser");
        newUser.setEmail("testuser@example.com");
        newUser.setPassword("encodedPassword");

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(jwtService.generateToken(any(Employee.class), anyString())).thenReturn("jwtToken");

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User registered successfully"))
                .andExpect(jsonPath("$.token").value("jwtToken"));
    }

    @Test
    void testLoginSuccessful() throws Exception {
        LoginRequest loginRequest = new LoginRequest("testuser@example.com", "password");
        String requestJson = objectMapper.writeValueAsString(loginRequest);

        Employee user = new Employee();
        user.setEmail("testuser@example.com");
        user.setPassword("encodedPassword");

        when(userService.findByEmail("testuser@example.com")).thenReturn(user);
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);
        when(jwtService.generateToken(any(Employee.class), anyString())).thenReturn("jwtToken");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Login successful"))
                .andExpect(jsonPath("$.token").value("jwtToken"));
    }

}
