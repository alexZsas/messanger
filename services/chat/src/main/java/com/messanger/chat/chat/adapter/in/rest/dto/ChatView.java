package com.messanger.chat.chat.adapter.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Accessors(chain = true)
public class ChatView {
    private String id;
    private Set<String> participants;
    @JsonFormat(pattern = "dd.MM.yyyy hh:mm:ss")
    private LocalDateTime createdAt;
}
