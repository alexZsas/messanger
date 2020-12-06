package com.messanger.auth.authority.adapter.out.stream.binding;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AuthorityEventBinding {
    String AUTHORITY_CREATED_EVENTS = "authorityCreatedEvents";
    String AUTHORITY_UPDATED_EVENTS = "authorityUpdatedEvents";
    String AUTHORITY_DELETED_EVENTS = "authorityDeletedEvents";

    @Output(AUTHORITY_CREATED_EVENTS)
    MessageChannel authorityCreated();

    @Output(AUTHORITY_UPDATED_EVENTS)
    MessageChannel authorityUpdated();

    @Output(AUTHORITY_DELETED_EVENTS)
    MessageChannel authorityDeleted();
}
