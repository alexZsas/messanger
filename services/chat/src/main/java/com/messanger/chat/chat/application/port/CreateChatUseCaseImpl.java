package com.messanger.chat.chat.application.port;

import com.messanger.chat.chat.application.port.in.CreateChatUseCase;
import com.messanger.chat.chat.application.port.out.CreateChatPort;
import com.messanger.chat.chat.domain.Chat;
import com.messanger.chat.chat.domain.command.CreateChatCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateChatUseCaseImpl implements CreateChatUseCase {
    private final CreateChatPort createChatPort;
    
    @Override
    public Mono<Chat> create(Mono<CreateChatCommand> command) {
        return command
                .map(Chat::new)
                .flatMap(chat -> createChatPort.create(Mono.just(chat)));
    }
}
