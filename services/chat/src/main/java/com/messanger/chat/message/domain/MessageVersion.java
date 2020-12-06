package com.messanger.chat.message.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MessageVersion {
    @Id
    private String id;
    private String message;
    private LocalDateTime createdAt;

    public MessageVersion(String message) {
        this.id = UUID.randomUUID().toString();
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }
}
