package com.messanger.auth.client.adapter.out.jpa.repository;

import com.messanger.auth.client.adapter.out.jpa.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClientRepository extends JpaRepository<ClientEntity, String> {
}
