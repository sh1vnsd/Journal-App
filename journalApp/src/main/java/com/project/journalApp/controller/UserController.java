package com.project.journalApp.controller;

import com.project.journalApp.entity.UserEntity;
import com.project.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Special Typo Component
@RequestMapping("/user") //Add mapping to whole class
public class UserController {

    //Specific Endpoints Will Be Written Here

    @Autowired
    private UserService userService;

    @GetMapping
    List<UserEntity> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody UserEntity user){
        userService.saveEntry(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user, @PathVariable String userName){
        UserEntity userInDb = userService.findByUserName(userName);
        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
