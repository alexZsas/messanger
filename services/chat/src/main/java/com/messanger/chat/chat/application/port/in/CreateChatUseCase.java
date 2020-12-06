package com.messanger.chat.chat.application.port.in;

import com.messanger.chat.chat.domain.Chat;
import com.messanger.chat.chat.domain.command.CreateChatCommand;
import reactor.core.publisher.Mono;

public interface CreateChatUseCase {
    Mono<Chat> create(Mono<CreateChatCommand> command);
}
