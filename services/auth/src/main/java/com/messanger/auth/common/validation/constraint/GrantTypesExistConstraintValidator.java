package com.messanger.auth.common.validation.constraint;

import com.messanger.auth.client.domain.GrantType;
import com.messanger.auth.common.validation.constraint.annotation.GrantTypesExist;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;

public class GrantTypesExistConstraintValidator implements ConstraintValidator<GrantTypesExist, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }

        java.util.List<String> notGrantType = new ArrayList<>();

        for (String split : value.split(",")) {
            if (!GrantType.isGrantType(split)) {
                notGrantType.add(split);
            }
        }

        if (!notGrantType.isEmpty()) {
            if (context instanceof HibernateConstraintValidatorContext) {
                context.unwrap(HibernateConstraintValidatorContext.class)
                        .addMessageParameter("notExistGrantTypes", notGrantType.toString());
            }
        }

        return notGrantType.isEmpty();
    }
}
