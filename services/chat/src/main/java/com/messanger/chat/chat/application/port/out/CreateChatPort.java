package com.messanger.chat.chat.application.port.out;

import com.messanger.chat.chat.domain.Chat;
import reactor.core.publisher.Mono;

public interface CreateChatPort {
    Mono<Chat> create(Mono<Chat> chat);
}
