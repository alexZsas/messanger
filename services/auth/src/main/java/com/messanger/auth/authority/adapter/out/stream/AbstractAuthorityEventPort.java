package com.messanger.auth.authority.adapter.out.stream;

import com.messanger.auth.authority.adapter.mapper.AuthorityMapper;
import com.messanger.auth.authority.adapter.out.stream.binding.AuthorityEventBinding;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractAuthorityEventPort {
    protected final AuthorityMapper mapper;
    protected final AuthorityEventBinding binding;
}
