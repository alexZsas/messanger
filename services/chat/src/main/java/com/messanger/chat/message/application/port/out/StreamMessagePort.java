package com.messanger.chat.message.application.port.out;

import com.messanger.chat.message.domain.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StreamMessagePort {
    Flux<Message> stream(Mono<String> chatId);
}
