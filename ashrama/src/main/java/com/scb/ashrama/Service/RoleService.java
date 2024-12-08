package com.scb.ashrama.Service;

import com.scb.ashrama.Domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    Role createRole(Role role);
    Role updateRole(Long id, Role role);
    boolean deleteRole(Long id);
}
