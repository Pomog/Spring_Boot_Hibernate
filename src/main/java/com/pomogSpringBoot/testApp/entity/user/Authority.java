package com.pomogSpringBoot.testApp.entity.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "authorities")
@NoArgsConstructor
@Data
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ToString.Exclude
    private User user;
    
    @Column(length = 50, nullable = false)
    private String authority;
}
