package com.project.journalApp.service;

import com.project.journalApp.entity.JournalEntity;
import com.project.journalApp.entity.UserEntity;
import com.project.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;



    @Transactional
    public void saveEntry(JournalEntity journalEntity, String userName){
        try {
            UserEntity user = userService.findByUserName(userName);
            JournalEntity saved = journalEntryRepo.save(journalEntity);
            user.getJournalEntries().add(saved);
//            user.setUserName(null);
            userService.saveUser(user);
        }
        catch (Exception e){
            throw new RuntimeException("An error occurred while saving the entry", e);
        }
    }

    public void saveEntry(JournalEntity journalEntity){
        journalEntryRepo.save(journalEntity);
    }

    public List<JournalEntity> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntity> findById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {
            UserEntity user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepo.deleteById(id);
            }
        } catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting the entry", e);
        }
        return removed;
    }

//    public List<JournalEntity> findByUserName(String userName){
//        return
//    }
}
