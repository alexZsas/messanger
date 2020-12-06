package com.messanger.auth.client.domain;

import com.messanger.auth.authority.domain.Authority;
import com.messanger.auth.client.domain.command.CreateClientCommand;
import com.messanger.auth.common.security.details.ExtendedClientDetails;
import com.messanger.auth.common.security.details.ExtendedClientDetailsImpl;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Client {
    private static final long serialVersionUID = 2377131682648593168L;

    private String id;
    private String password;
    private Set<String> scope;
    private Set<String> autoApproveScopes;
    private Set<GrantType> grantTypes;
    private Collection<Authority> authorities;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private Map<String, Object> additionalInfo;
    private boolean enabled;

    public Client(CreateClientCommand command) {
        this.id = Optional.ofNullable(command.getClientId()).orElseGet(() -> UUID.randomUUID().toString());
        this.password = command.getPassword();
        this.scope = command.getScope();
        this.autoApproveScopes = command.getAutoApproveScopes();
        this.grantTypes = command.getGrantTypes();
        this.authorities = command.getAuthorities();
        this.accessTokenValiditySeconds = command.getAccessTokenValiditySeconds();
        this.refreshTokenValiditySeconds = command.getRefreshTokenValiditySeconds();
        this.additionalInfo = command.getAdditionalInfo();
        this.enabled = true;
    }

    public ExtendedClientDetails toClientDetails() {
        ExtendedClientDetailsImpl clientDetails = new ExtendedClientDetailsImpl();
        clientDetails.setClientId(id);
        clientDetails.setClientSecret(password);
        clientDetails.setScope(scope);
        if (autoApproveScopes != null) {
            clientDetails.setAutoApproveScopes(autoApproveScopes);
        }
        clientDetails.setAuthorizedGrantTypes(stringGrantTypes());
        if (authorities != null) {
            clientDetails.setAuthorities(authorities);
        }
        clientDetails.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
        clientDetails.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);
        if (additionalInfo != null) {
            clientDetails.setAdditionalInformation(additionalInfo);
        }
        clientDetails.setEnabled(enabled);
        return clientDetails;
    }

    private Collection<String> stringGrantTypes() {
        return Optional.ofNullable(grantTypes).stream()
                .flatMap(Collection::stream)
                .map(GrantType::getGrantTypeName)
                .collect(Collectors.toSet());
    }
}
