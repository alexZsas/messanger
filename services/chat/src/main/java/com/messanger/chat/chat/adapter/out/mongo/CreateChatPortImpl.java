package com.messanger.chat.chat.adapter.out.mongo;

import com.messanger.chat.chat.adapter.out.mongo.repository.ChatRepository;
import com.messanger.chat.chat.application.port.out.CreateChatPort;
import com.messanger.chat.chat.domain.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateChatPortImpl implements CreateChatPort {
    private final ChatRepository chatRepository;
    
    @Override
    public Mono<Chat> create(Mono<Chat> chat) {
        return chat.flatMap(chatRepository::save);
    }
}
