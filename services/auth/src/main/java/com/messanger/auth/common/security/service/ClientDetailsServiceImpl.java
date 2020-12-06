package com.messanger.auth.common.security.service;

import com.messanger.auth.client.application.port.out.GetClientByIdPort;
import com.messanger.auth.client.domain.Client;
import com.messanger.auth.common.exception.ClientDisabledException;
import com.messanger.auth.common.security.details.ExtendedClientDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;

@RequiredArgsConstructor
@SuppressWarnings("deprecation")
public class ClientDetailsServiceImpl implements ClientDetailsService {
    private final GetClientByIdPort getClientByIdPort;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ExtendedClientDetails clientDetails = getClientByIdPort.get(clientId)
                .map(Client::toClientDetails)
                .orElseThrow(() -> new NoSuchClientException("Client with id '" + clientId + "' not found."));
        if (!clientDetails.isEnabled()) {
            throw new ClientDisabledException("Client with id '" + clientId + " was disabled.");
        }

        return clientDetails;
    }
}
