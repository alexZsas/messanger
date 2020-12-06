package com.messanger.chat.message.application.port.out;

import com.messanger.chat.message.domain.Message;
import reactor.core.publisher.Mono;

public interface CreateMessagePort {
    Mono<Message> create(Mono<Message> message);
}
