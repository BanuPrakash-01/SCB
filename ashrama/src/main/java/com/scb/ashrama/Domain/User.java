package com.scb.ashrama.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @ManyToMany(cascade = CascadeType.MERGE) // Use MERGE to avoid detached entity issues
    @JoinTable(
            name = "user_roles", // The name of the join table
            joinColumns = @JoinColumn(name = "user_id"), // This is the foreign key for User in user_roles
            inverseJoinColumns = @JoinColumn(name = "role_id") // This is the foreign key for Role in user_roles
    )
    private Set<Role> roles = new HashSet<>();

    @Setter
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
