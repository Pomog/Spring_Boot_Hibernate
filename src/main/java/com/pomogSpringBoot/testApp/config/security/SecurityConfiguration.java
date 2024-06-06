package com.pomogSpringBoot.testApp.config.security;

import com.pomogSpringBoot.testApp.config.UserRole;
import com.pomogSpringBoot.testApp.service.dbService.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/CSS/**", "/js/**", "/img/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole(UserRole.USER.name(), UserRole.MANAGER.name(), UserRole.ADMIN.name())
                                .requestMatchers(HttpMethod.POST, "/api/**").hasAnyRole(UserRole.MANAGER.name(), UserRole.ADMIN.name())
                                .requestMatchers(HttpMethod.PUT, "/api/**").hasAnyRole(UserRole.MANAGER.name(), UserRole.ADMIN.name())
                                .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole(UserRole.ADMIN.name())
                                .requestMatchers(HttpMethod.GET, "/**").hasAnyRole(UserRole.USER.name(), UserRole.MANAGER.name(), UserRole.ADMIN.name())
                                .requestMatchers(HttpMethod.POST, "/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.MANAGER.name())
                                .requestMatchers(HttpMethod.DELETE, "/**").hasAnyRole(UserRole.ADMIN.name())
                )
                .formLogin(form ->
                        form
                                .loginPage("/login-page")
                                .loginProcessingUrl("/perform_login")
                                .defaultSuccessUrl("/",true)
                                .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                ;
//        http.httpBasic(Customizer.withDefaults());
//        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}