package com.project.journalApp.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries") //This annotation tells that this entity is mapped with mongodb collection
@Data
@NoArgsConstructor
public class JournalEntity {

    @Id
    private ObjectId id;
            //Datatype for mongodb id

    @NonNull
    private String title;

    private String content;

    private LocalDateTime date;

}
