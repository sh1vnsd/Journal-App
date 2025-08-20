package com.project.journalApp.repository;

import com.project.journalApp.entity.UserEntity;
import com.project.journalApp.service.UserArgumentsProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepositoryImplTests {

    @Autowired
    private UserRepoImpl userRepo;

    @Test
    public void testSaveNewUser() {
        Assertions.assertNotNull(userRepo.getUserForSA());
    }
}
