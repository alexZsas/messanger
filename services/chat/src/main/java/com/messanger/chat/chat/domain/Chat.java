package com.messanger.chat.chat.domain;

import com.messanger.chat.chat.domain.command.CreateChatCommand;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "chats")
public class Chat {
    @Id
    private String id;
    private Set<String> participants = new HashSet<>();

    @Version
    private long versionNumber;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public Chat(CreateChatCommand command) {
        this.id = UUID.randomUUID().toString();
        this.participants.addAll(command.getParticipants());
    }
}
