package com.messanger.auth.client.adapter.out.stream;

import com.messanger.auth.client.adapter.mapper.ClientMapper;
import com.messanger.auth.client.adapter.out.stream.binging.ClientEventBinding;
import com.messanger.auth.client.application.port.out.SendClientUpdatedEventPort;
import com.messanger.auth.client.domain.Client;
import com.messanger.common.api.authapi.event.AuthEvents;
import com.messanger.common.api.event.Events;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class SendClientUpdatedEventPortImpl extends AbstractClientEventPort implements SendClientUpdatedEventPort {

    public SendClientUpdatedEventPortImpl(ClientEventBinding binding, ClientMapper mapper) {
        super(binding, mapper);
    }

    @Override
    public void send(Client client) {
        AuthEvents.ClientUpdated event = AuthEvents.ClientUpdated.builder()
                .metadata(Events.Metadata.createDefault())
                .payload(mapper.toClientDto(client))
                .build();

        binding.clientUpdated().send(new GenericMessage<>(event, Events.headersWithKey(event)));
    }
}
