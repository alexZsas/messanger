package com.messanger.chat.message.domain.command;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Validated
public class CreateMessageCommand {
    @NotBlank
    private String message;
    @NotEmpty
    private String chatId;
    @NotEmpty
    private String sender;
}
