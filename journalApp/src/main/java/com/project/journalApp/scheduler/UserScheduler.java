package com.project.journalApp.scheduler;

import com.project.journalApp.cache.AppCache;
import com.project.journalApp.entity.JournalEntity;
import com.project.journalApp.entity.UserEntity;
import com.project.journalApp.repository.ConfigJournalAppRepo;
import com.project.journalApp.repository.UserRepo;
import com.project.journalApp.repository.UserRepoImpl;
import com.project.journalApp.service.EmailService;
import com.project.journalApp.service.SentimentAnalysisService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepoImpl userRepo;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;


    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaMail(){
        List<UserEntity> users = userRepo.getUserForSA();

        for(var user : users){
            List<JournalEntity> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().
                    isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).
                    map(x -> x.getContent()).collect(Collectors.toList());

            String entry = String.join(" ", filteredEntries);

            String sentiment = sentimentAnalysisService.getSentiment(entry);

            emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", sentiment);
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        appCache.init();
    }
}
