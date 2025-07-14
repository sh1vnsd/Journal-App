package com.project.journalApp.service;

import com.project.journalApp.entity.JournalEntity;
import com.project.journalApp.entity.UserEntity;
import com.project.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public void saveEntry(UserEntity userEntity){
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
