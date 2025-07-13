package com.project.journalApp.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries") //This annotation tells that this entity is mapped with mongodb collection
@Data
public class JournalEntry {

    @Id     //Datatype for mongodb id
    private ObjectId id;

    private String title;

    private String content;

    private LocalDateTime date;

}
