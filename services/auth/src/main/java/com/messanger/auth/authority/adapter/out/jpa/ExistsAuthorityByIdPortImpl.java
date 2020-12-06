package com.messanger.auth.authority.adapter.out.jpa;

import com.messanger.auth.authority.adapter.mapper.AuthorityMapper;
import com.messanger.auth.authority.adapter.out.jpa.repository.JpaAuthorityRepository;
import com.messanger.auth.authority.application.port.out.ExistsAuthorityByIdPort;
import org.springframework.stereotype.Component;

@Component
public class ExistsAuthorityByIdPortImpl extends AbstractJpaAuthorityPort implements ExistsAuthorityByIdPort {
    public ExistsAuthorityByIdPortImpl(AuthorityMapper mapper, JpaAuthorityRepository repository) {
        super(mapper, repository);
    }

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }
}
