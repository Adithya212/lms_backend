package com.application.controller;

import com.application.dto.LoginRequest;
import com.application.dto.SignupRequest;
import com.application.exception.InvalidCredentialsException;
import com.application.jwt.JwtService;
import com.application.model.Employee;
import com.application.services.ApiUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:54024")
@RequiredArgsConstructor
public class AuthController {

    private final ApiUserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){
        Employee newUser = new Employee();
        newUser.setUserName(signupRequest.getUsername());
        newUser.setEmail(signupRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        // Save user to DB (Ensure email uniqueness)
        userService.saveUser(newUser);

        String token = jwtService.generateToken(newUser, newUser.getEmail());
        return ResponseEntity.ok(Map.of("message", "User registered successfully", "token", token));
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        AppUser user = userService.findByEmail(loginRequest.getEmail());
//        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
//            String token = jwtService.generateToken(user, user.getEmail());
//
//            // Respond with a custom message and the token
//            return ResponseEntity.ok(Map.of(
//                    "message", "Login successful",
//                    "token", token
//            ));
//        } else {
//            return ResponseEntity.status(401).body("Invalid credentials");
//        }
//    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Employee user = userService.findByEmail(loginRequest.getEmail());

        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = jwtService.generateToken(user, user.getEmail());
        return ResponseEntity.ok(Map.of("message", "Login successful", "token", token));
    }


    @GetMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Invalid Authorization header");
        }

        String token = authorizationHeader.substring(7); // Remove "Bearer " prefix

        if (jwtService.validateToken(token)) {
            String username = jwtService.extractUsername(token);
            return ResponseEntity.ok("Token is valid. Logged in as: " + username);
        } else {
            return ResponseEntity.status(401).body("Invalid token");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<Employee>> getAllUsers() {
        List<Employee> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}