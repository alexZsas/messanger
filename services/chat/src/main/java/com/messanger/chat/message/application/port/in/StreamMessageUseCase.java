package com.messanger.chat.message.application.port.in;

import com.messanger.chat.message.domain.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StreamMessageUseCase {
    Flux<Message> stream(Mono<String> chatId);
}
