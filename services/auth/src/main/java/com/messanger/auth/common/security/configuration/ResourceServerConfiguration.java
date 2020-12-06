package com.messanger.auth.common.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
@RequiredArgsConstructor
@SuppressWarnings("deprecation")
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private final TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(tokenStore)
                .tokenExtractor(new BearerTokenExtractor());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .antMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").access("hasRole('ADMIN') && #oauth2.hasScope('/auth:get')")
                .antMatchers(HttpMethod.POST, "/api/**").access("hasRole('ADMIN') && #oauth2.hasScope('/auth:create')")
                .antMatchers(HttpMethod.PUT, "/api/**").access("hasRole('ADMIN') && #oauth2.hasScope('/auth:update')")
                .antMatchers(HttpMethod.DELETE, "/api/**").access("hasRole('ADMIN') && #oauth2.hasScope('/auth:update')");
    }
}
