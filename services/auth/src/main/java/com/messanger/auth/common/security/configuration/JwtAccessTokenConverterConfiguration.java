package com.messanger.auth.common.security.configuration;

import com.messanger.auth.common.security.service.ReloadingJwtAccessTokenConverter;
import com.messanger.auth.common.security.service.key.DefaultKeyPairProvider;
import com.messanger.auth.common.security.service.key.KeyPairProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

@Configuration
public class JwtAccessTokenConverterConfiguration {

    @Bean
    @Primary
    public ReloadingJwtAccessTokenConverter accessTokenConverter() {
        return new ReloadingJwtAccessTokenConverter(provider());
    }

    @Bean
    public KeyPairProvider provider() {
        return new DefaultKeyPairProvider(TimeUnit.DAYS, 10);
    }
}
