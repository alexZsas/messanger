package com.messanger.auth.user.adapter.out.stream;

import com.messanger.auth.user.adapter.mapper.UserMapper;
import com.messanger.auth.user.adapter.out.stream.binging.UserEventBinding;
import com.messanger.auth.user.application.port.out.SendUserDeletedEventPort;
import com.messanger.common.api.authapi.event.AuthEvents;
import com.messanger.common.api.event.Events;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class SendUserDeletedEventPortImpl extends AbstractUserEventPort implements SendUserDeletedEventPort {

    public SendUserDeletedEventPortImpl(UserEventBinding binding, UserMapper userMapper) {
        super(binding, userMapper);
    }

    @Override
    public void send(String userId) {
        AuthEvents.UserDeleted event = AuthEvents.UserDeleted.builder()
                .metadata(Events.Metadata.createDefault())
                .payload(userId)
                .build();

        binding.userDeleted().send(new GenericMessage<>(event, Events.headersWithKey(event)));
    }
}
