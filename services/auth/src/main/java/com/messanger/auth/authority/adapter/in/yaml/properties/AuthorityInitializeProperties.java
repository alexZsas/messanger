package com.messanger.auth.authority.adapter.in.yaml.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Data
@Component
@Validated
@ConfigurationProperties("authorities")
public class AuthorityInitializeProperties {
    private Collection<@Valid AuthorityInfo> authorityInfos;

    @Data
    public static class AuthorityInfo {
        @NotBlank(message = "{authority.name.not-blank}")
        private String name;
    }
}
