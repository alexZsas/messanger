package com.messanger.auth.user.adapter.in.yaml.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Data
@Component
@Validated
@ConfigurationProperties("users")
public class UserInitializeProperties {
    private Collection<@Valid UserInfo> userInfos;

    @Data
    public static class UserInfo {
        @NotBlank(message = "{user.username.not-blank}")
        private String username;
        @NotBlank(message = "{user.password.not-blank}")
        @Pattern(
                message = "{user.password.pattern}",
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"
        )
        private String password;
        private String authorities;
    }
}
