package com.messanger.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@EnableMongoAuditing
@SpringBootApplication
@EnableReactiveMongoRepositories
public class ChatApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class);
    }
}
