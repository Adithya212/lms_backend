package com.application.service;

import com.application.exception.UserAlreadyExistsException;
import com.application.model.Employee;
import com.application.repository.UserRepository;
import com.application.services.ApiUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ApiUserServiceTest {

    @InjectMocks
    private ApiUserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test case for loadUserByUsername (success case)
    @Test
    void testLoadUserByUsername_Success() {
        Employee employee = new Employee();
        employee.setUserName("testuser");
        employee.setPassword("password");

        when(userRepository.findByUserName("testuser")).thenReturn(Optional.of(employee));

        UserDetails userDetails = userService.loadUserByUsername("testuser");

        assertEquals("testuser", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
    }

    // Test case for loadUserByUsername (user not found)
    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByUserName("nonexistent")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("nonexistent"));
    }

    // Test case for findByEmail (success case)
    @Test
    void testFindByEmail_Success() {
        Employee employee = new Employee();
        employee.setEmail("testuser@example.com");
        employee.setPassword("password");

        when(userRepository.findByEmail("testuser@example.com")).thenReturn(Optional.of(employee));

        Employee foundUser = userService.findByEmail("testuser@example.com");

        assertEquals("testuser@example.com", foundUser.getEmail());
        assertEquals("password", foundUser.getPassword());
    }

    // Test case for findByEmail (user not found)
    @Test
    void testFindByEmail_UserNotFound() {
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.findByEmail("nonexistent@example.com"));
    }

    // Test case for saveUser (success case)
    @Test
    void testSaveUser_Success() {
        Employee employee = new Employee();
        employee.setEmail("newuser@example.com");
        employee.setPassword("password");

        when(userRepository.findByEmail("newuser@example.com")).thenReturn(Optional.empty());

        userService.saveUser(employee);

        verify(userRepository, times(1)).save(employee);
    }

    // Test case for saveUser (email already exists)
    @Test
    void testSaveUser_EmailAlreadyExists() {
        Employee existingUser = new Employee();
        existingUser.setEmail("existing@example.com");

        when(userRepository.findByEmail("existing@example.com")).thenReturn(Optional.of(existingUser));

        Employee newUser = new Employee();
        newUser.setEmail("existing@example.com");

        assertThrows(UserAlreadyExistsException.class, () -> userService.saveUser(newUser));
    }

    // Test case for getAllUsers
    @Test
    void testGetAllUsers() {
        userService.getAllUsers();
        verify(userRepository, times(1)).findAll();
    }
}
