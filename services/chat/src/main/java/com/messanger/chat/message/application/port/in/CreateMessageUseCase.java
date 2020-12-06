package com.messanger.chat.message.application.port.in;

import com.messanger.chat.message.domain.Message;
import com.messanger.chat.message.domain.command.CreateMessageCommand;
import reactor.core.publisher.Mono;

public interface CreateMessageUseCase {
    Mono<Message> create(Mono<CreateMessageCommand> command);
}
