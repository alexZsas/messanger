package com.messanger.chat.chat.adapter.out.mongo.repository;

import com.messanger.chat.chat.domain.Chat;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends ReactiveMongoRepository<Chat, String> {
}
