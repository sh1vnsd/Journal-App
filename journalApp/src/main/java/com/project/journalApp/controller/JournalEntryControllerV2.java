package com.project.journalApp.controller;

import com.project.journalApp.entity.JournalEntry;
import com.project.journalApp.repository.JournalEntryRepo;
import com.project.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //Special Typo Component
@RequestMapping("/journal") //Add mapping to whole class
public class JournalEntryControllerV2 {

    //Specific Endpoints Will Be Written Here

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry entry) {
        entry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(entry);
        return entry;
    }

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }


    @GetMapping("id/{id}")
    public JournalEntry getEntryById(@PathVariable ObjectId id) {
        return journalEntryService.findById(id).orElse(null);
                                             //Using orElse cuz Optional is a box which may or may not have a data so
                                             //we return data if data exists orElse we return null
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId) {
         journalEntryService.deleteById(myId);
         return true;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);

        return old;
    }
}
