package com.messanger.chat.message.adapter.out.mongo;

import com.messanger.chat.message.adapter.out.mongo.repository.MessageRepository;
import com.messanger.chat.message.application.port.out.CreateMessagePort;
import com.messanger.chat.message.domain.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateMessagePortImpl implements CreateMessagePort {
    private final MessageRepository messageRepository;
    
    @Override
    public Mono<Message> create(Mono<Message> message) {
        return message.flatMap(messageRepository::save);
    }
}
