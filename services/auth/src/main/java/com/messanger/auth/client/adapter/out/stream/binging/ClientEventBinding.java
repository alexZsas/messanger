package com.messanger.auth.client.adapter.out.stream.binging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ClientEventBinding {
    String CLIENT_CREATED_EVENTS = "clientCreatedEvents";
    String CLIENT_UPDATED_EVENTS = "clientUpdatedEvents";
    String CLIENT_DELETED_EVENTS = "clientDeletedEvents";

    @Output(CLIENT_CREATED_EVENTS)
    MessageChannel clientCreated();

    @Output(CLIENT_UPDATED_EVENTS)
    MessageChannel clientUpdated();

    @Output(CLIENT_DELETED_EVENTS)
    MessageChannel clientDeleted();
}
