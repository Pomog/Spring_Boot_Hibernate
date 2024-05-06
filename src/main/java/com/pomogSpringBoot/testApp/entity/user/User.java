package com.pomogSpringBoot.testApp.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {
    @Id
    @Column(length = 50)
    private String username;
    
    @Column(length = 500, nullable = false)
    private String password;
    
    @Column(nullable = false)
    private boolean enabled;
}