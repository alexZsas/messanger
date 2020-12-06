package com.messanger.auth.authority.adapter.out.stream;

import com.messanger.auth.authority.adapter.mapper.AuthorityMapper;
import com.messanger.auth.authority.adapter.out.stream.binding.AuthorityEventBinding;
import com.messanger.auth.authority.application.port.out.SendAuthorityDeletedEventPort;
import com.messanger.common.api.authapi.event.AuthEvents;
import com.messanger.common.api.event.Events;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class SendAuthorityDeletedEventPortImpl extends AbstractAuthorityEventPort
        implements SendAuthorityDeletedEventPort {

    public SendAuthorityDeletedEventPortImpl(AuthorityMapper mapper, AuthorityEventBinding binding) {
        super(mapper, binding);
    }

    @Override
    public void send(String authorityId) {
        AuthEvents.AuthorityDeleted event = AuthEvents.AuthorityDeleted.builder()
                .metadata(Events.Metadata.createDefault())
                .payload(authorityId)
                .build();

        binding.authorityDeleted().send(new GenericMessage<>(event, Events.headersWithKey(event)));
    }
}
