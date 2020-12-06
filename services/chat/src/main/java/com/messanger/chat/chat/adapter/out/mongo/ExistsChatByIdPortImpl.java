package com.messanger.chat.chat.adapter.out.mongo;

import com.messanger.chat.chat.adapter.out.mongo.repository.ChatRepository;
import com.messanger.chat.chat.application.port.out.ExistsChatByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ExistsChatByIdPortImpl implements ExistsChatByIdPort {
    private final ChatRepository chatRepository;
    
    @Override
    public Mono<Boolean> exists(Mono<String> id) {
        return chatRepository.existsById(id);
    }
}
