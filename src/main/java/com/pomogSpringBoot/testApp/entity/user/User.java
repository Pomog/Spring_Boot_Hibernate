package com.pomogSpringBoot.testApp.entity.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {
    @Id
    @Column(length = 50, unique = true)
    private String username;
    
    @Column(length = 68, nullable = false)
    private String password;
    
    @Column(nullable = false)
    private boolean enabled;
    
    @Column(length = 50, nullable = false, unique = true)
    private String email;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Authority> authorities;
}