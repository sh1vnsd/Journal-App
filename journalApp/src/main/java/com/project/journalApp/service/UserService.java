package com.project.journalApp.service;

import com.project.journalApp.entity.UserEntity;
import com.project.journalApp.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    //Private static final -> cuz no reassignment + one instance should only be made
//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public boolean saveNewUser(UserEntity userEntity){
        try{
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userEntity.setRoles(Arrays.asList("USER"));
            userRepo.save(userEntity);
            return true;
        } catch (Exception e){
            log.error("Error occured for {} :", userEntity.getUserName());
            log.info("hahahahaahah");
            log.warn("hahahahaahah");
            log.debug("hahahahaahah");
//            log.trace("hahahahaahah");
            return false;
        }
    }

    public void saveAdmin(UserEntity userEntity){
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepo.save(userEntity);
    }

    public void saveUser(UserEntity userEntity){
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
