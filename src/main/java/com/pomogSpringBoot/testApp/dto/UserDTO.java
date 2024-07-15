package com.pomogSpringBoot.testApp.dto;

import com.pomogSpringBoot.testApp.entity.user.Authority;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class UserDTO {

    @NotBlank
    private String username;
    
    @NotBlank
    private String password;

    private boolean enabled;
    
    @NotBlank
    private String email;

    private List<Authority> authorities;
    
    public UserDTO(String username, String password, boolean enabled, String email, List<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
        this.authorities = authorities;
    }
}
