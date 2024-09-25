package com.application.services;

import java.util.List;
import java.util.Optional;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.model.User;
import com.application.repository.UserRepository;

@Service
public class UserService 
{ @Autowired
private UserRepository userRepository;

	// Create a new user
	public User createUser(User user) {
		return userRepository.save(user);
	}

	// Get a user by email
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findById(email);
	}

	// Get all users
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// Update an existing user
	public User updateUser(String email, User userDetails) {
		User user = userRepository.findById(email)
				.orElseThrow(() -> new RuntimeException("User not found with email: " + email));

		user.setUsername(userDetails.getUsername());
		user.setUserid(userDetails.getUserid());
		user.setMobile(userDetails.getMobile());
		user.setGender(userDetails.getGender());
		user.setProfession(userDetails.getProfession());
		user.setAddress(userDetails.getAddress());
		user.setPassword(userDetails.getPassword());

		return userRepository.save(user);
	}

	// Delete a user by email
	public void deleteUser(String email) {
		User user = userRepository.findById(email)
				.orElseThrow(() -> new RuntimeException("User not found with email: " + email));
		userRepository.delete(user);
	}
}