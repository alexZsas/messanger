package com.messanger.auth.common.validation.constraint;

import com.messanger.auth.authority.application.port.out.GetExistingAuthoritiesByIdsPort;
import com.messanger.auth.authority.application.port.out.GetExistingAuthoritiesByNamesPort;
import com.messanger.auth.common.validation.constraint.annotation.AuthoritiesExist;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

@RequiredArgsConstructor
public abstract class AbstractAuthoritiesExistConstraintValidator<T>
        implements ConstraintValidator<AuthoritiesExist, T> {
    protected final GetExistingAuthoritiesByIdsPort getExistingAuthoritiesByIdsPort;
    protected final GetExistingAuthoritiesByNamesPort getExistingAuthoritiesByNamesPort;

    protected void addNotExistAuthorities(ConstraintValidatorContext context, Collection<String> notExist) {
        if (context instanceof HibernateConstraintValidatorContext) {
            context.unwrap(HibernateConstraintValidatorContext.class)
                    .addMessageParameter("notExistAuthorities", notExist.toString());
        }
    }

}
