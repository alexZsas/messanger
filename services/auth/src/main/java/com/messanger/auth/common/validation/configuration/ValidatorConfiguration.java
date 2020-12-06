package com.messanger.auth.common.validation.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Configuration
public class ValidatorConfiguration {
    @Value("${messages.base-names}")
    private String[] baseNames;

    @Bean
    public ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setDefaultLocale(new Locale("ru"));
        messageSource.setBasenames(baseNames);
        return messageSource;
    }

    @Bean
    @Primary
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setMessageInterpolator(new MessageInterpolatorFactory().getObject());
        factoryBean.setValidationMessageSource(reloadableResourceBundleMessageSource());
        return factoryBean;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public Validator getValidator() {
                return localValidatorFactoryBean();
            }
        };
    }
}
