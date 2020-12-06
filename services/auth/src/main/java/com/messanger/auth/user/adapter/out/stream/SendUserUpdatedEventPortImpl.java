package com.messanger.auth.user.adapter.out.stream;

import com.messanger.auth.user.adapter.mapper.UserMapper;
import com.messanger.auth.user.adapter.out.stream.binging.UserEventBinding;
import com.messanger.auth.user.application.port.out.SendUserUpdatedEventPort;
import com.messanger.auth.user.domain.User;
import com.messanger.common.api.authapi.event.AuthEvents;
import com.messanger.common.api.event.Events;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class SendUserUpdatedEventPortImpl extends AbstractUserEventPort implements SendUserUpdatedEventPort {

    public SendUserUpdatedEventPortImpl(UserEventBinding binding, UserMapper userMapper) {
        super(binding, userMapper);
    }

    @Override
    public void send(User user) {
        AuthEvents.UserUpdated event = AuthEvents.UserUpdated.builder()
                .metadata(Events.Metadata.createDefault())
                .payload(userMapper.toUserDto(user))
                .build();

        binding.userUpdated().send(new GenericMessage<>(event, Events.headersWithKey(event)));
    }
}
