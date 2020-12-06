package com.messanger.auth.authority.adapter.out.jpa;

import com.messanger.auth.authority.adapter.mapper.AuthorityMapper;
import com.messanger.auth.authority.adapter.out.jpa.repository.JpaAuthorityRepository;
import com.messanger.auth.authority.application.port.out.CreateAuthorityPort;
import com.messanger.auth.authority.domain.Authority;
import org.springframework.stereotype.Component;

@Component
public class CreateAuthorityPortImpl extends AbstractJpaAuthorityPort implements CreateAuthorityPort {

    public CreateAuthorityPortImpl(AuthorityMapper mapper, JpaAuthorityRepository repository) {
        super(mapper, repository);
    }

    @Override
    public void create(Authority authority) {
        repository.save(mapper.toAuthorityEntity(authority));
    }
}
