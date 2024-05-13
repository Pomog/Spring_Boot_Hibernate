package com.pomogSpringBoot.testApp.config.security;

import com.pomogSpringBoot.testApp.config.UserRole;
import com.pomogSpringBoot.testApp.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }

    @Bean
    public UserDetailsManager userDetailsManager (DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, enabled from users where username=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username, authority from authorities where username=?");
        return jdbcUserDetailsManager;
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
                        .requestMatchers(HttpMethod.POST, "/**").hasRole(UserRole.ADMIN.name())
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}