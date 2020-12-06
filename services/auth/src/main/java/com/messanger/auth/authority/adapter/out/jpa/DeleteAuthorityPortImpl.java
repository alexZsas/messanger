package com.messanger.auth.authority.adapter.out.jpa;

import com.messanger.auth.authority.adapter.mapper.AuthorityMapper;
import com.messanger.auth.authority.adapter.out.jpa.repository.JpaAuthorityRepository;
import com.messanger.auth.authority.application.port.out.DeleteAuthorityPort;
import org.springframework.stereotype.Component;

@Component
public class DeleteAuthorityPortImpl extends AbstractJpaAuthorityPort implements DeleteAuthorityPort {
    public DeleteAuthorityPortImpl(AuthorityMapper mapper, JpaAuthorityRepository repository) {
        super(mapper, repository);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
