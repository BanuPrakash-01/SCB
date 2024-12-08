package com.scb.ashrama.Service.impl;

import com.scb.ashrama.Domain.Role;
import com.scb.ashrama.Domain.User;
import com.scb.ashrama.Repository.RoleRepository;
import com.scb.ashrama.Repository.UserRepository;
import com.scb.ashrama.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j  // Enables SLF4J logging
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    // Method to create a new user
    @Override
    public User createUser(User user, Role role) {
        log.info("Creating user with provided role");

        // Save the role if not already in the database
        if (role != null && role.getId() == null) {
            role = roleRepository.save(role); // Save the new role if it doesn't exist
        }

        // Assign the role to the user
        user.setRole(role); // Assuming user can have one role only

        // Save the user
        User savedUser = userRepository.save(user);
        log.info("User created successfully with ID: {}", savedUser.getId());
        return savedUser;
    }

    // Method to update an existing user
    @Override
    public User updateUser(Long userId, User updatedUser, String updatedBy) {
        log.info("Updating user with ID: {}", userId);

        Optional<User> existingUserOpt = userRepository.findById(userId);

        if (!existingUserOpt.isPresent()) {
            log.error("User with ID {} not found for update", userId);
            throw new IllegalArgumentException("User with id " + userId + " not found");
        }

        User existingUser = existingUserOpt.get();

        // Update user details
        if (updatedUser.getUsername() != null) {
            existingUser.setUsername(updatedUser.getUsername());
        }

        if (updatedUser.getPassword() != null) {
            // No password encoding, just update the password directly
            existingUser.setPassword(updatedUser.getPassword());
        }

        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }

        // Update role if provided
        if (updatedUser.getRole() != null) {
            existingUser.setRole(updatedUser.getRole());
        }

        // Update the 'updatedBy' field
        existingUser.setUpdatedBy(updatedBy);

        // Save and return the updated user
        User updatedUserEntity = userRepository.save(existingUser);
        log.info("User updated successfully with ID: {}", updatedUserEntity.getId());
        return updatedUserEntity;
    }

    // Method to delete a user by ID
    @Override
    public void deleteUser(Long userId) {
        log.info("Deleting user with ID: {}", userId);
        userRepository.deleteById(userId);
        log.info("User with ID: {} deleted successfully", userId);
    }

    // Method to get a user by ID
    @Override
    public User getUserById(Long userId) {
        log.info("Fetching user with ID: {}", userId);

        Optional<User> userOpt = userRepository.findById(userId);
        User user = userOpt.orElseThrow(() -> {
            log.error("User with ID {} not found", userId);
            return new IllegalArgumentException("User with id " + userId + " not found");
        });

        log.info("User with ID: {} found", userId);
        return user;
    }

    // Method to get all users
    @Override
    public List<User> getAllUsers() {
        log.info("Fetching all users");
        List<User> users = userRepository.findAll();
        log.info("Found {} users", users.size());
        return users;
    }
}
