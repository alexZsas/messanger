package com.messanger.auth.client.application;

import com.messanger.auth.client.application.port.in.PageClientsUseCase;
import com.messanger.auth.client.application.port.out.PageClientsPort;
import com.messanger.auth.client.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageClientsUseCaseImpl implements PageClientsUseCase {
    private final PageClientsPort pageClientsPort;

    @Override
    public Page<Client> page(Pageable pageable) {
        return pageClientsPort.get(pageable);
    }
}
