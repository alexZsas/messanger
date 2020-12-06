package com.messanger.auth.client.adapter.out.stream;

import com.messanger.auth.client.adapter.mapper.ClientMapper;
import com.messanger.auth.client.adapter.out.stream.binging.ClientEventBinding;
import com.messanger.auth.client.application.port.out.SendClientDeletedEventPort;
import com.messanger.common.api.authapi.event.AuthEvents;
import com.messanger.common.api.event.Events;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class SendClientDeletedEventPortImpl extends AbstractClientEventPort implements SendClientDeletedEventPort {

    public SendClientDeletedEventPortImpl(ClientEventBinding binding, ClientMapper mapper) {
        super(binding, mapper);
    }

    @Override
    public void send(String clientId) {
        AuthEvents.ClientDeleted event = AuthEvents.ClientDeleted.builder()
                .metadata(Events.Metadata.createDefault())
                .payload(clientId)
                .build();

        binding.clientDeleted().send(new GenericMessage<>(event, Events.headersWithKey(event)));
    }
}
