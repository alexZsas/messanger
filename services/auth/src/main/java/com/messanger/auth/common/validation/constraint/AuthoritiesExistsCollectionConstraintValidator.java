package com.messanger.auth.common.validation.constraint;

import com.messanger.auth.authority.application.port.out.GetExistingAuthoritiesByIdsPort;
import com.messanger.auth.authority.application.port.out.GetExistingAuthoritiesByNamesPort;
import com.messanger.auth.authority.domain.Authority;

import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class AuthoritiesExistsCollectionConstraintValidator
        extends AbstractAuthoritiesExistConstraintValidator<Collection<Authority>> {

    public AuthoritiesExistsCollectionConstraintValidator(
            GetExistingAuthoritiesByIdsPort getExistingAuthoritiesByIdsPort,
            GetExistingAuthoritiesByNamesPort getExistingAuthoritiesByNamesPort
    ) {
        super(getExistingAuthoritiesByIdsPort, getExistingAuthoritiesByNamesPort);
    }

    @Override
    public boolean isValid(Collection<Authority> value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        Collection<String> authorityIds = value.stream()
                .map(Authority::getId)
                .collect(Collectors.toCollection(ArrayList::new));
        Collection<Authority> exist = getExistingAuthoritiesByIdsPort.get(authorityIds);

        if (exist.isEmpty()) {
            addNotExistAuthorities(context, authorityIds);
            return false;
        }

        Collection<String> existIds = exist.stream()
                .map(Authority::getId)
                .collect(Collectors.toList());

        authorityIds.removeAll(existIds);

        if (!authorityIds.isEmpty()) {
            addNotExistAuthorities(context, authorityIds);
            return false;
        }

        return true;
    }
}
