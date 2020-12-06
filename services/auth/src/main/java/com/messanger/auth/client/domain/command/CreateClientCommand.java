package com.messanger.auth.client.domain.command;

import com.messanger.auth.authority.domain.Authority;
import com.messanger.auth.client.domain.GrantType;
import com.messanger.auth.common.validation.constraint.annotation.AuthoritiesExist;
import com.messanger.auth.common.validation.constraint.annotation.PasswordAndConfirmationEqual;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@PasswordAndConfirmationEqual
public class CreateClientCommand {
    private String clientId;
    @NotEmpty(message = "{client.password.not-blank}")
    @Pattern(
            message = "{client.password.pattern}",
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"
    )
    private String password;
    @NotEmpty(message = "{client.password.not-blank}")
    @Pattern(
            message = "{client.password.pattern}",
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"
    )
    private String confirmPassword;
    private Set<String> scope;
    private Set<String> autoApproveScopes;
    @NotEmpty(message = "{client.grant-type.not-empty}")
    private Set<GrantType> grantTypes;
    @AuthoritiesExist
    private Collection<Authority> authorities;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private Map<String, Object> additionalInfo;
}
