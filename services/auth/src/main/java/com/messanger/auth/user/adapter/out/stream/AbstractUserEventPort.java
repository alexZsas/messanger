package com.messanger.auth.user.adapter.out.stream;

import com.messanger.auth.user.adapter.mapper.UserMapper;
import com.messanger.auth.user.adapter.out.stream.binging.UserEventBinding;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractUserEventPort {
    protected final UserEventBinding binding;
    protected final UserMapper userMapper;
}
