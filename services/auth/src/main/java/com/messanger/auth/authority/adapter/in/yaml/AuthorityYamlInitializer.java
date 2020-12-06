package com.messanger.auth.authority.adapter.in.yaml;

import com.messanger.auth.authority.adapter.in.yaml.properties.AuthorityInitializeProperties;
import com.messanger.auth.authority.adapter.in.yaml.properties.AuthorityInitializeProperties.AuthorityInfo;
import com.messanger.auth.authority.adapter.mapper.AuthorityMapper;
import com.messanger.auth.authority.application.port.in.CreateAuthorityUseCase;
import com.messanger.auth.authority.application.port.in.ListAuthoritiesByNamesUseCase;
import com.messanger.auth.authority.domain.Authority;
import com.messanger.auth.authority.domain.command.CreateAuthorityCommand;
import com.messanger.auth.client.adapter.in.yaml.ClientYamlInitializer;
import com.messanger.auth.common.initializer.Initializer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthorityYamlInitializer implements Initializer {
    public static final int ORDER = ClientYamlInitializer.ORDER - 100;

    private final AuthorityMapper mapper;
    private final AuthorityInitializeProperties authorityInitializeProperties;
    private final ListAuthoritiesByNamesUseCase listAuthoritiesUseCase;
    private final CreateAuthorityUseCase createAuthorityUseCase;

    @Override
    public void init() {
        Collection<AuthorityInfo> authorityInfos = authorityInitializeProperties.getAuthorityInfos();
        if (!CollectionUtils.isEmpty(authorityInfos)) {
            Collection<String> authorityNames = authorityInfos.stream()
                    .map(AuthorityInfo::getName)
                    .collect(Collectors.toList());

            Set<String> existingAuthorities = listAuthoritiesUseCase.get(authorityNames).stream()
                    .map(Authority::getAuthority)
                    .collect(Collectors.toSet());

            for (AuthorityInfo authorityInfo : authorityInfos) {
                if (!existingAuthorities.contains(authorityInfo.getName())) {
                    CreateAuthorityCommand command = mapper.toCommand(authorityInfo);
                    createAuthorityUseCase.create(command);
                }
            }
        }
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
