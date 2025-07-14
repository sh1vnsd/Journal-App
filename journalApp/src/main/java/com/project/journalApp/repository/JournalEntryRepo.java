package com.project.journalApp.repository;

import com.project.journalApp.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryRepo extends MongoRepository<JournalEntity, ObjectId> {

}
