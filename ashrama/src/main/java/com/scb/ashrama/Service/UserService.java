package com.scb.ashrama.Service;

import com.scb.ashrama.Domain.Role;
import com.scb.ashrama.Domain.User;
import java.util.List;

public interface UserService {
    User createUser(User user, Role role); // Create a new user
    User updateUser(Long userId, User updatedUser, String updatedBy); // Update an existing user
    void deleteUser(Long userId); // Delete a user by ID
    User getUserById(Long userId); // Get user by ID
    List<User> getAllUsers(); // Get all users
}
