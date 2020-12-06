package com.messanger.chat.chat.application.port.out;

import reactor.core.publisher.Mono;

public interface ExistsChatByIdPort {
    Mono<Boolean> exists(Mono<String> id);
}
