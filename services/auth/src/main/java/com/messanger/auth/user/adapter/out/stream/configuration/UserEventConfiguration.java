package com.messanger.auth.user.adapter.out.stream.configuration;

import com.messanger.auth.user.adapter.out.stream.binging.UserEventBinding;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(UserEventBinding.class)
public class UserEventConfiguration {
}
