package com.messanger.chat.chat.adapter.in.rest;

import com.messanger.chat.chat.adapter.in.rest.dto.ChatView;
import com.messanger.chat.chat.adapter.mapper.ChatMapper;
import com.messanger.chat.chat.application.port.in.CreateChatUseCase;
import com.messanger.chat.chat.domain.command.CreateChatCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chats")
public class ChatController {
    private final CreateChatUseCase createChatUseCase;
    private final ChatMapper chatMapper;
    
    @PostMapping
    public Mono<ChatView> create(@RequestBody @Validated Mono<CreateChatCommand> command) {
        return createChatUseCase.create(command)
                .map(chatMapper::mapView);
    }
}
