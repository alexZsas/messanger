package com.messanger.auth.user.adapter.out.jpa.repository;

import com.messanger.auth.user.adapter.out.jpa.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByUsername(String username);

    List<UserEntity> findAllByUsernameIn(Collection<String> usernames);

    Optional<UserEntity> findByUsername(String username);
}
