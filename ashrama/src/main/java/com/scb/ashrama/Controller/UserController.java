package com.scb.ashrama.Controller;

import com.scb.ashrama.Domain.Role;
import com.scb.ashrama.Domain.User;
import com.scb.ashrama.Service.UserService;
import com.scb.ashrama.Repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j  // Lombok annotation to generate a logger field
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    // Updated createUser method to accept a User and Role object
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.info("Received request to create user: {}", user); // Log the user data

        if (user.getRole() != null) {
            // Check if the role is present in the database
            Role roleFromDb = roleRepository.findById(user.getRole().getId())
                    .orElseThrow(() -> {
                        log.error("Role not found: {}", user.getRole().getId()); // Log error if role is not found
                        return new IllegalArgumentException("Role not found");
                    });

            // Assign the role to the user
            user.setRole(roleFromDb);
        } else {
            log.error("Role must be provided for user: {}", user); // Log error if no role is provided
            throw new IllegalArgumentException("Role must be provided");
        }

        User newUser = userService.createUser(user, user.getRole());
        log.info("User created successfully: {}", newUser); // Log success
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser, @RequestParam String updatedBy) {
        log.info("Received request to update user with ID: {}", userId);

        User user = userService.updateUser(userId, updatedUser, updatedBy);
        if (user != null) {
            log.info("User updated successfully: {}", user); // Log success
            return ResponseEntity.ok(user);
        } else {
            log.warn("User with ID {} not found for update", userId); // Log a warning if user not found
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        log.info("Received request to delete user with ID: {}", userId);

        userService.deleteUser(userId);
        log.info("User with ID {} deleted successfully", userId); // Log success
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        log.info("Received request to get user with ID: {}", userId);

        User user = userService.getUserById(userId);
        if (user != null) {
            log.info("User found: {}", user); // Log found user
            return ResponseEntity.ok(user);
        } else {
            log.warn("User with ID {} not found", userId); // Log a warning if user not found
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Received request to get all users");

        List<User> users = userService.getAllUsers();
        log.info("Returning {} users", users.size()); // Log the number of users
        return ResponseEntity.ok(users);
    }
}
