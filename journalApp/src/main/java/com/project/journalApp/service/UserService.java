package com.project.journalApp.service;

import com.project.journalApp.entity.JournalEntity;
import com.project.journalApp.entity.UserEntity;
import com.project.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(UserEntity userEntity){
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(Arrays.asList("USER"));
        userRepo.save(userEntity);
    }

    public void saveNewUser(UserEntity userEntity){
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepo.save(userEntity);
    }

    public List<UserEntity> getAll(){
        return userRepo.findAll();
    }

    public Optional<UserEntity> findById(ObjectId id){
        return userRepo.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepo.deleteById(id);
    }

    public UserEntity findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }
}
