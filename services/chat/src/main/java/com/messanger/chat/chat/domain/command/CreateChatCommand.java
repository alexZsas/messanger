package com.messanger.chat.chat.domain.command;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Validated
public class CreateChatCommand {
    @NotEmpty
    private Set<String> participants;
}
