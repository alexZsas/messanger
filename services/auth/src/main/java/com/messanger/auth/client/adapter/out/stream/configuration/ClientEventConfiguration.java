package com.messanger.auth.client.adapter.out.stream.configuration;

import com.messanger.auth.client.adapter.out.stream.binging.ClientEventBinding;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(ClientEventBinding.class)
public class ClientEventConfiguration {
}
