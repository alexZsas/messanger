package com.messanger.auth.common.validation.constraint.annotation;

import com.messanger.auth.common.validation.constraint.PasswordAndConfirmationEqualConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordAndConfirmationEqualConstraintValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
@Repeatable(PasswordAndConfirmationEqual.List.class)
public @interface PasswordAndConfirmationEqual {

    String message() default "{com.messanger.auth.common.validation.constraint.annotation.GrantTypesExist.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String passwordField() default "password";

    String passwordConfirmationField() default "confirmPassword";

    @Target({TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        PasswordAndConfirmationEqual[] value();
    }
}
