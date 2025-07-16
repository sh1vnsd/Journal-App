package com.project.journalApp.entity;


import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "users") //This annotation tells that this entity is mapped with mongodb collection
@Data
public class UserEntity {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String userName;

    @NonNull
    private String password;

    @DBRef //This annotation means we are creating a referrence of journalEntries in User entity
            //Acting like a foreign key
    private List<JournalEntity> journalEntries = new ArrayList<>();

    private List<String> roles;
}
