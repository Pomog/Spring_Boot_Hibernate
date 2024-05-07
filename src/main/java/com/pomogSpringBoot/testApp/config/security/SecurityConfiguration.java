package com.pomogSpringBoot.testApp.config.security;

import com.pomogSpringBoot.testApp.config.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

    @Bean
    public UserDetailsManager userDetailsManager (DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole(UserRole.USER.name(), UserRole.MANAGER.name(), UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/api/**").hasAnyRole(UserRole.MANAGER.name(), UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/api/**").hasAnyRole(UserRole.MANAGER.name(), UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/**").hasRole(UserRole.ADMIN.name())
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}


//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails testUser = User.builder()
//                .username("User")
//                .password("{noop}123")
//                .roles(UserRole.USER.name())
//                .build();
//
//        UserDetails testManager = User.builder()
//                .username("Manager")
//                .password("{noop}1234")
//                .roles(UserRole.MANAGER.name())
//                .build();
//
//        UserDetails testAdmin = User.builder()
//                .username("Admin")
//                .password("{noop}12345")
//                .roles(UserRole.ADMIN.name())
//                .build();
//
//        return new InMemoryUserDetailsManager(testUser, testManager, testAdmin);
//    }
