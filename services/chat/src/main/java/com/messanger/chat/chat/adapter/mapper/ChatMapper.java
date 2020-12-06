package com.messanger.chat.chat.adapter.mapper;

import com.messanger.chat.chat.adapter.in.rest.dto.ChatView;
import com.messanger.chat.chat.domain.Chat;
import com.messanger.chat.common.mapper.CommonMapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfig.class)
public abstract class ChatMapper {
    public abstract ChatView mapView(Chat chat);
}
