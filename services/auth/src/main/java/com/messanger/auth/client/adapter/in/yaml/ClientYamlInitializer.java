package com.messanger.auth.client.adapter.in.yaml;

import com.messanger.auth.authority.application.port.in.ListAuthoritiesByNamesUseCase;
import com.messanger.auth.authority.domain.Authority;
import com.messanger.auth.client.adapter.in.yaml.properties.ClientInitializeProperties;
import com.messanger.auth.client.adapter.mapper.ClientMapper;
import com.messanger.auth.client.application.port.in.CreateClientUseCase;
import com.messanger.auth.client.application.port.in.ListClientsUseCase;
import com.messanger.auth.client.domain.Client;
import com.messanger.auth.client.domain.command.CreateClientCommand;
import com.messanger.auth.common.initializer.Initializer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientYamlInitializer implements Initializer {
    public static final int ORDER = 1000;

    private final ClientMapper clientMapper;
    private final ClientInitializeProperties clientInitializeProperties;
    private final CreateClientUseCase createClientUseCase;
    private final ListClientsUseCase listClientsUseCase;
    private final ListAuthoritiesByNamesUseCase listAuthoritiesByNamesUseCase;

    @Override
    public void init() {
        Collection<ClientInitializeProperties.ClientInfo> clientInfos = clientInitializeProperties.getClientInfos();

        if (!CollectionUtils.isEmpty(clientInfos)) {
            Collection<String> clientIds = clientInfos.stream()
                    .map(ClientInitializeProperties.ClientInfo::getId)
                    .collect(Collectors.toList());

            Set<String> existClientIds = listClientsUseCase.list(clientIds).stream()
                    .map(Client::getId)
                    .collect(Collectors.toSet());

            for (ClientInitializeProperties.ClientInfo clientInfo : clientInfos) {
                if (!existClientIds.contains(clientInfo.getId())) {
                    CreateClientCommand command = clientMapper.mapToCommand(clientInfo);
                    fillAuthorities(command);
                    createClientUseCase.create(command);
                }
            }
        }
    }

    private void fillAuthorities(CreateClientCommand command) {
        List<String> authorityNames = command.getAuthorities().stream()
                .map(Authority::getAuthority)
                .collect(Collectors.toList());
        command.setAuthorities(listAuthoritiesByNamesUseCase.get(authorityNames));
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
