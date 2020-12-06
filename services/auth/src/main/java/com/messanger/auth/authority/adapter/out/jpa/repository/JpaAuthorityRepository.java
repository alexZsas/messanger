package com.messanger.auth.authority.adapter.out.jpa.repository;

import com.messanger.auth.authority.adapter.out.jpa.model.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface JpaAuthorityRepository extends JpaRepository<AuthorityEntity, String> {
    Collection<AuthorityEntity> findAllByNameIn(Collection<String> authorityNames);

    boolean existsByName(String name);
}
