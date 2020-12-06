package com.messanger.auth.client.adapter.out.stream;

import com.messanger.auth.client.adapter.mapper.ClientMapper;
import com.messanger.auth.client.adapter.out.stream.binging.ClientEventBinding;
import com.messanger.auth.client.domain.Client;
import com.messanger.auth.user.adapter.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractClientEventPort {
    protected final ClientEventBinding binding;
    protected final ClientMapper mapper;
}
