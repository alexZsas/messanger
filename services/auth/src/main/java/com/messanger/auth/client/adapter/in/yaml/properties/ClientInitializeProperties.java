package com.messanger.auth.client.adapter.in.yaml.properties;

import com.messanger.auth.common.validation.constraint.annotation.GrantTypesExist;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Map;

@Data
@Component
@Validated
@ConfigurationProperties("clients")
public class ClientInitializeProperties {
    private Collection<@Valid ClientInfo> clientInfos;

    @Data
    @Validated
    public static class ClientInfo {
        @NotBlank(message = "{client.id.not-blank}")
        private String id;
        @NotBlank(message = "{client.password.not-blank}")
        @Pattern(
                message = "{client-info.password.pattern}",
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"
        )
        private String password;
        private String scope;
        private String autoApproveScopes;
        @NotBlank(message = "{client.grant-type.not-blank}")
        @GrantTypesExist
        private String grantTypes;
        private String authorities;
        private Integer accessTokenValiditySeconds;
        private Integer refreshTokenValiditySeconds;
        private Map<String, Object> additionalInfo;
    }
}
