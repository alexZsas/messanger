package com.messanger.auth.client.adapter.mapper;

import com.messanger.auth.authority.adapter.out.jpa.model.AuthorityEntity;
import com.messanger.auth.authority.domain.Authority;
import com.messanger.auth.client.adapter.in.yaml.properties.ClientInitializeProperties;
import com.messanger.auth.client.adapter.out.jpa.model.ClientEntity;
import com.messanger.auth.client.domain.Client;
import com.messanger.auth.client.domain.GrantType;
import com.messanger.auth.client.domain.command.CreateClientCommand;
import com.messanger.auth.common.mapper.CommonMapperConfig;
import com.messanger.common.api.authapi.AuthApi;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import static com.messanger.auth.common.mapper.MapperHelper.mapSplit;

@Mapper(config = CommonMapperConfig.class)
public abstract class ClientMapper {
    @Autowired
    private ObjectMapper objectMapper;

    public CreateClientCommand mapToCommand(ClientInitializeProperties.ClientInfo clientInfo) {
        return new CreateClientCommand()
                .setClientId(clientInfo.getId())
                .setPassword(clientInfo.getPassword())
                .setConfirmPassword(clientInfo.getPassword())
                .setScope(mapSplit(clientInfo.getScope(), () -> new HashSet<>()))
                .setAutoApproveScopes(mapSplit(clientInfo.getAutoApproveScopes(), () -> new HashSet<>()))
                .setGrantTypes(mapSplit(clientInfo.getGrantTypes(), GrantType::of, HashSet::new))
                .setAuthorities(mapSplit(clientInfo.getAuthorities(), name -> new Authority(null, name)))
                .setAccessTokenValiditySeconds(clientInfo.getAccessTokenValiditySeconds())
                .setRefreshTokenValiditySeconds(clientInfo.getRefreshTokenValiditySeconds())
                .setAdditionalInfo(clientInfo.getAdditionalInfo());
    }

    @Mapping(source = "autoApproveScope", target = "autoApproveScopes")
    public abstract Client mapClient(ClientEntity entity);

    public Authority mapAuthority(AuthorityEntity authorityEntity) {
        return new Authority(authorityEntity.getId(), authorityEntity.getName());
    }

    public AuthorityEntity mapAuthority(Authority authority) {
        return new AuthorityEntity()
                .setId(authority.getId())
                .setName(authority.getAuthority());
    }

    public Map<String, Object> mapNode(JsonNode jsonNode) {
        return objectMapper.convertValue(jsonNode, new TypeReference<>() {});
    }

    public JsonNode mapNode(Map<String, Object> map) {
        return objectMapper.valueToTree(map);
    }

    @Mapping(source = "autoApproveScopes", target = "autoApproveScope")
    public abstract ClientEntity mapClient(Client client);

    public abstract Collection<Client> mapClients(Collection<ClientEntity> entity);

    public Page<Client> mapClients(Page<ClientEntity> page) {
        return new PageImpl<>(
                new ArrayList<>(mapClients(page.getContent())),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    public abstract AuthApi.Client toClientDto(Client client);

    public abstract AuthApi.Authority toAuthorityDto(Authority authority);
}
