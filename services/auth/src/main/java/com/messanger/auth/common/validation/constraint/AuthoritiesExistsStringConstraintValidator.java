package com.messanger.auth.common.validation.constraint;

import com.messanger.auth.authority.application.port.out.GetExistingAuthoritiesByIdsPort;
import com.messanger.auth.authority.application.port.out.GetExistingAuthoritiesByNamesPort;
import com.messanger.auth.authority.domain.Authority;
import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidatorContext;
import java.util.Collection;
import java.util.stream.Collectors;

public class AuthoritiesExistsStringConstraintValidator extends AbstractAuthoritiesExistConstraintValidator<String> {

    public AuthoritiesExistsStringConstraintValidator(GetExistingAuthoritiesByIdsPort getExistingAuthoritiesByIdsPort,
                                                      GetExistingAuthoritiesByNamesPort getExistingAuthoritiesByNamesPort) {
        super(getExistingAuthoritiesByIdsPort, getExistingAuthoritiesByNamesPort);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }

        Collection<String> authorities = Lists.newArrayList(value.split(","));

        Collection<String> existByIds = getExistingAuthoritiesByIdsPort.get(authorities).stream()
                .map(Authority::getId)
                .collect(Collectors.toList());
        authorities.removeAll(existByIds);
        if (authorities.isEmpty()) {
            return true;
        }

        Collection<String> existsByNames = getExistingAuthoritiesByNamesPort.get(authorities).stream()
                .map(Authority::getAuthority)
                .collect(Collectors.toList());
        authorities.removeAll(existsByNames);
        if (authorities.isEmpty()) {
            return true;
        }

        addNotExistAuthorities(context, authorities);
        return false;
    }
}
