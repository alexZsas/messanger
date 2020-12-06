package com.messanger.chat.message.adapter.in.rest;

import com.messanger.chat.message.adapter.in.rest.dto.MessageView;
import com.messanger.chat.message.adapter.mapper.MessageMapper;
import com.messanger.chat.message.application.port.in.CreateMessageUseCase;
import com.messanger.chat.message.application.port.in.StreamMessageUseCase;
import com.messanger.chat.message.domain.command.CreateMessageCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {
    private final StreamMessageUseCase streamMessageUseCase;
    private final CreateMessageUseCase createMessageUseCase;
    private final MessageMapper messageMapper;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MessageView> create(@RequestBody @Validated Mono<CreateMessageCommand> createMessageCommand) {
        return createMessageUseCase.create(createMessageCommand)
                .map(messageMapper::mapView);
    }
    
    @GetMapping(value = "/{chatId}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<MessageView> stream(@PathVariable("chatId") String chatId) {
        return streamMessageUseCase.stream(Mono.defer(() -> Mono.just(chatId)))
                .map(messageMapper::mapView);
    }
}
