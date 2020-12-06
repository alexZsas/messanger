package com.messanger.chat.message.application;

import com.messanger.chat.chat.application.port.out.ExistsChatByIdPort;
import com.messanger.chat.common.exception.Exceptions;
import com.messanger.chat.message.application.port.in.StreamMessageUseCase;
import com.messanger.chat.message.application.port.out.StreamMessagePort;
import com.messanger.chat.message.domain.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StreamMessageUseCaseImpl implements StreamMessageUseCase {
    private final StreamMessagePort streamMessagePort;
    private final ExistsChatByIdPort existsChatByIdPort;

    @Override
    public Flux<Message> stream(Mono<String> chatId) {
        return chatId
                .filterWhen(id -> existsChatByIdPort.exists(Mono.just(id)))
                .switchIfEmpty(Mono.defer(() -> chatId.flatMap(id -> Mono.error(Exceptions.CHAT_NOT_FOUND.create()
                        .message("Chat with id {} not found.", id)
                        .get()))))
                .flatMapMany(id -> streamMessagePort.stream(Mono.just(id)));
    }
}
