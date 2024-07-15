package com.pomogSpringBoot.testApp.rest;

import com.pomogSpringBoot.testApp.dto.UserDTO;
import com.pomogSpringBoot.testApp.entity.user.User;
import com.pomogSpringBoot.testApp.service.dataService.ObjectTranformer;
import com.pomogSpringBoot.testApp.service.dbService.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private final UserService userService;
    private final ObjectTranformer<UserDTO, User> userDTOUserObjectTranformer;
    
    @Autowired
    public UserRestController(UserService userService, ObjectTranformer<UserDTO, User> userDTOUserObjectTranformer) {
        this.userService = userService;
        this.userDTOUserObjectTranformer = userDTOUserObjectTranformer;
    }
    
    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody @NonNull UserDTO userDTO) {
        try {
            userService.saveUser(userDTOUserObjectTranformer.transform(userDTO));
            return ResponseEntity.ok("Data received and saved successfully");
        } catch (Exception e){
            return ResponseEntity.status(500).body("Error processing data: " + e.getMessage());
        }
    }
}
