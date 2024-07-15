package com.pomogSpringBoot.testApp.service.dataService;

import com.pomogSpringBoot.testApp.dto.UserDTO;
import com.pomogSpringBoot.testApp.entity.glassware.LabGlassware;
import com.pomogSpringBoot.testApp.entity.user.Authority;
import com.pomogSpringBoot.testApp.entity.user.User;
import com.pomogSpringBoot.testApp.model.LabGlasswareModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserToDTOtransformer implements ObjectTranformer<UserDTO, User>{
    
    @Override
    public User transform(UserDTO source) {
        User userFromDTO = new User();

        List<Authority> authorityList = new ArrayList<>();
        source.getAuthorities().forEach(authority -> {
            authority.setUser(userFromDTO);
            authorityList.add(authority);
                }
        );
        
        userFromDTO.setUsername(source.getUsername());
        userFromDTO.setEmail(source.getEmail());
        userFromDTO.setPassword(source.getPassword());
        userFromDTO.setAuthorities(authorityList);
        
        return userFromDTO;
    }
}
