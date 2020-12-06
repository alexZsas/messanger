package com.messanger.chat.message.adapter.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class MessageView {
    private String id;
    private String message;
    private String chatId;
    private String sender;
    @JsonFormat(pattern = "dd.MM.yyyy hh:mm:ss")
    private LocalDateTime createdAt;
}
