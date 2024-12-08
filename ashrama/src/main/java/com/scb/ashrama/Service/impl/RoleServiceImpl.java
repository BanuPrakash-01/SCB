package com.scb.ashrama.Service.impl;

import com.scb.ashrama.Domain.Role;
import com.scb.ashrama.Repository.RoleRepository;
import com.scb.ashrama.Service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j  // Lombok annotation for logging
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        log.info("Fetching all roles");
        List<Role> roles = roleRepository.findAll();
        log.info("Retrieved {} roles", roles.size());
        return roles;
    }

    @Override
    public Role getRoleById(Long id) {
        log.info("Fetching role with ID: {}", id);
        Role role = roleRepository.findById(id).orElse(null);
        if (role != null) {
            log.info("Role found with ID: {}", id);
        } else {
            log.warn("Role with ID: {} not found", id);
        }
        return role;
    }

    @Override
    public Role createRole(Role role) {
        log.info("Creating role: {}", role);
        Role createdRole = roleRepository.save(role);
        log.info("Role created with ID: {}", createdRole.getId());
        return createdRole;
    }

    @Override
    public Role updateRole(Long id, Role role) {
        log.info("Updating role with ID: {}", id);
        if (roleRepository.existsById(id)) {
            role.setId(id); // Assuming there's a setId method in the Role class
            Role updatedRole = roleRepository.save(role);
            log.info("Role updated with ID: {}", updatedRole.getId());
            return updatedRole;
        } else {
            log.warn("Role with ID: {} not found for update", id);
            return null;
        }
    }

    @Override
    public boolean deleteRole(Long id) {
        log.info("Request to delete role with ID: {}", id);
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            log.info("Role deleted with ID: {}", id);
            return true;
        } else {
            log.warn("Role with ID: {} not found for deletion", id);
            return false;
        }
    }
}
