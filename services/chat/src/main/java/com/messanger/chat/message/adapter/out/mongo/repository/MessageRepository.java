package com.messanger.chat.message.adapter.out.mongo.repository;

import com.messanger.chat.message.domain.Message;
import org.reactivestreams.Publisher;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MessageRepository extends ReactiveMongoRepository<Message, String> {
    @Tailable
    Flux<Message> streamByChatId(Publisher<String> chatId);
}
