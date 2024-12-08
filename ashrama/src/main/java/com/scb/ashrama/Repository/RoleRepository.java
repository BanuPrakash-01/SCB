package com.scb.ashrama.Repository;

import com.scb.ashrama.Domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Additional query methods (if needed) can be added here
}
