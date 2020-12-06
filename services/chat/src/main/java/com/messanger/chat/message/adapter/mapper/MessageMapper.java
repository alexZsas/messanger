package com.messanger.chat.message.adapter.mapper;

import com.messanger.chat.common.mapper.CommonMapperConfig;
import com.messanger.chat.message.adapter.in.rest.dto.MessageView;
import com.messanger.chat.message.domain.Message;
import com.messanger.chat.message.domain.MessageVersion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CommonMapperConfig.class)
public abstract class MessageMapper {
    public MessageView mapView(Message message) {
        MessageVersion version = getLast(message.getVersions());
        return new MessageView()
                .setId(message.getId())
                .setChatId(message.getChatId())
                .setSender(message.getSender())
                .setMessage(version.getMessage())
                .setCreatedAt(version.getCreatedAt());
    }

    private MessageVersion getLast(List<MessageVersion> versions) {
        return versions.get(versions.size() - 1);
    }
}
