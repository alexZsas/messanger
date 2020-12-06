package com.messanger.auth.client.adapter.out.stream;

import com.messanger.auth.client.adapter.mapper.ClientMapper;
import com.messanger.auth.client.adapter.out.stream.binging.ClientEventBinding;
import com.messanger.auth.client.application.port.out.SendClientCreatedEventPort;
import com.messanger.auth.client.domain.Client;
import com.messanger.common.api.authapi.event.AuthEvents;
import com.messanger.common.api.event.Events;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class SendClientCreatedEventPortImpl extends AbstractClientEventPort implements SendClientCreatedEventPort {

    public SendClientCreatedEventPortImpl(ClientEventBinding binding, ClientMapper mapper) {
        super(binding, mapper);
    }

    @Override
    public void send(Client client) {
        AuthEvents.ClientCreated event = AuthEvents.ClientCreated.builder()
                .metadata(Events.Metadata.createDefault())
                .payload(mapper.toClientDto(client))
                .build();

        binding.clientCreated().send(new GenericMessage<>(event, Events.headersWithKey(event)));
    }
}
