package com.messanger.chat.message.adapter.out.mongo;

import com.messanger.chat.message.adapter.out.mongo.repository.MessageRepository;
import com.messanger.chat.message.application.port.out.StreamMessagePort;
import com.messanger.chat.message.domain.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StreamMessagePortImpl implements StreamMessagePort {
    private final MessageRepository messageRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;
    
    @Override
    public Flux<Message> stream(Mono<String> chatId) {
        return chatId
                .map(id -> new Query(Criteria.where("chatId").is(id)))
                .flatMapMany(query -> reactiveMongoTemplate.tail(query, Message.class));
    }
}
