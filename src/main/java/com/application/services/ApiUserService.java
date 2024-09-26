package com.application.services;

import com.application.exception.UserAlreadyExistsException;
import com.application.model.Employee;
import com.application.repository.UserRepository;
//import com.application.model.Employee;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee dbUser = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(dbUser.getUsername())
                .password(dbUser.getPassword())
                .build();
    }

    public Employee findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void saveUser(Employee appUser) {
        if (userRepository.findByEmail(appUser.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already exists: " + appUser.getEmail());
        }
        userRepository.save(appUser);
    }

    public List<Employee> getAllUsers() {
        return userRepository.findAll();
    }


}
