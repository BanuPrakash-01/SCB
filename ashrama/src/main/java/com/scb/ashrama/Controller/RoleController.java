package com.scb.ashrama.Controller;

import com.scb.ashrama.Domain.Role;
import com.scb.ashrama.Service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Slf4j  // Lombok annotation to add logging capability
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        log.info("Fetching all roles");
        List<Role> roles = roleService.getAllRoles();
        log.info("Retrieved {} roles", roles.size());
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        log.info("Fetching role with ID: {}", id);
        Role role = roleService.getRoleById(id);
        if (role != null) {
            log.info("Role found with ID: {}", id);
            return ResponseEntity.ok(role);
        } else {
            log.warn("Role with ID: {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        log.info("Creating new role: {}", role);
        Role createdRole = roleService.createRole(role);
        log.info("Created role with ID: {}", createdRole.getId());
        return ResponseEntity.status(201).body(createdRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
        log.info("Updating role with ID: {}", id);
        Role updatedRole = roleService.updateRole(id, role);
        if (updatedRole != null) {
            log.info("Updated role with ID: {}", id);
            return ResponseEntity.ok(updatedRole);
        } else {
            log.warn("Role with ID: {} not found for update", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        log.info("Request to delete role with ID: {}", id);
        boolean isDeleted = roleService.deleteRole(id);
        if (isDeleted) {
            log.info("Deleted role with ID: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            log.warn("Role with ID: {} not found for deletion", id);
            return ResponseEntity.notFound().build();
        }
    }
}
