package com.messanger.auth.client.application.port.in;

import com.messanger.auth.client.domain.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PageClientsUseCase {
    Page<Client> page(Pageable pageable);
}
