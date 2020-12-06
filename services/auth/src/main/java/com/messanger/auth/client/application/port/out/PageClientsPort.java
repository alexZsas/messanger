package com.messanger.auth.client.application.port.out;

import com.messanger.auth.client.domain.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PageClientsPort {
    Page<Client> get(Pageable pageable);
}
