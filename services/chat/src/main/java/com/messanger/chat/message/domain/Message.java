package com.messanger.chat.message.domain;

import com.messanger.chat.message.domain.command.CreateMessageCommand;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "messages")
public class Message {
    @Id
    private String id;
    private String chatId;
    private String sender;
    private List<MessageVersion> versions = new ArrayList<>();
    
    @Version
    private long versionNumber;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public Message(CreateMessageCommand command) {
        this.id = UUID.randomUUID().toString();
        this.chatId = command.getChatId();
        this.sender = command.getSender();
        this.versions.add(new MessageVersion(command.getMessage()));
    }
}
