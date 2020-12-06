package com.messanger.auth.authority.adapter.out.stream;

import com.messanger.auth.authority.adapter.mapper.AuthorityMapper;
import com.messanger.auth.authority.adapter.out.stream.binding.AuthorityEventBinding;
import com.messanger.auth.authority.application.port.out.SendAuthorityCreatedEventPort;
import com.messanger.auth.authority.domain.Authority;
import com.messanger.common.api.authapi.event.AuthEvents;
import com.messanger.common.api.event.Events;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class SendAuthorityCreatedEventPortImpl extends AbstractAuthorityEventPort
        implements SendAuthorityCreatedEventPort {

    public SendAuthorityCreatedEventPortImpl(AuthorityMapper mapper, AuthorityEventBinding binding) {
        super(mapper, binding);
    }

    @Override
    public void send(Authority authority) {
        AuthEvents.AuthorityCreated event = AuthEvents.AuthorityCreated.builder()
                .metadata(Events.Metadata.createDefault())
                .payload(mapper.toAuthorityDto(authority))
                .build();

        binding.authorityCreated().send(new GenericMessage<>(event, Events.headersWithKey(event)));
    }
}
