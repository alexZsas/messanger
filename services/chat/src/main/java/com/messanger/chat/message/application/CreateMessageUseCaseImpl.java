package com.messanger.chat.message.application;

import com.messanger.chat.chat.application.port.out.ExistsChatByIdPort;
import com.messanger.chat.common.exception.Exceptions;
import com.messanger.chat.message.application.port.in.CreateMessageUseCase;
import com.messanger.chat.message.application.port.out.CreateMessagePort;
import com.messanger.chat.message.domain.Message;
import com.messanger.chat.message.domain.command.CreateMessageCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateMessageUseCaseImpl implements CreateMessageUseCase {
    private final CreateMessagePort createMessagePort;
    private final ExistsChatByIdPort existsChatByIdPort;

    @Override
    public Mono<Message> create(Mono<CreateMessageCommand> command) {
        return command
                .filterWhen(c -> existsChatByIdPort.exists(Mono.just(c.getChatId())))
                .switchIfEmpty(Mono.defer(() -> command.flatMap(c -> Mono.error(Exceptions.CHAT_NOT_FOUND.create()
                        .message("Chat with id {} not found.", c.getChatId())
                        .get()))))
                .map(Message::new)
                .flatMap(message -> createMessagePort.create(Mono.just(message)));

    }
}
