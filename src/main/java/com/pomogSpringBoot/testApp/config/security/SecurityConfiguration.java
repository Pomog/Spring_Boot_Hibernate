package com.pomogSpringBoot.testApp.config.security;

import com.pomogSpringBoot.testApp.config.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails testUser = User.builder()
                .username("Yurii")
                .password("{noop}123")
                .roles(UserRole.USER.name())
                .build();
        
        UserDetails testManager = User.builder()
                .username("Manager")
                .password("{noop}1234")
                .roles(UserRole.MANAGER.name())
                .build();
        
        return new InMemoryUserDetailsManager(testUser, testManager);
    }
}
