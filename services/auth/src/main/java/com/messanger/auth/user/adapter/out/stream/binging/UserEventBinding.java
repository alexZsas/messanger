package com.messanger.auth.user.adapter.out.stream.binging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserEventBinding {
    String USER_CREATED_EVENTS = "userCreatedEvents";
    String USER_UPDATED_EVENTS = "userUpdatedEvents";
    String USER_DELETED_EVENTS = "userDeletedEvents";

    @Output(USER_CREATED_EVENTS)
    MessageChannel userCreated();

    @Output(USER_UPDATED_EVENTS)
    MessageChannel userUpdated();

    @Output(USER_DELETED_EVENTS)
    MessageChannel userDeleted();
}
