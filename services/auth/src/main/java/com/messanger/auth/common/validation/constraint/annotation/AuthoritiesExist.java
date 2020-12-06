package com.messanger.auth.common.validation.constraint.annotation;

import com.messanger.auth.common.validation.constraint.AuthoritiesExistsCollectionConstraintValidator;
import com.messanger.auth.common.validation.constraint.AuthoritiesExistsStringConstraintValidator;
import com.messanger.auth.common.validation.constraint.annotation.AuthoritiesExist.List;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {
        AuthoritiesExistsStringConstraintValidator.class,
        AuthoritiesExistsCollectionConstraintValidator.class

})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(List.class)
public @interface AuthoritiesExist {

    String message() default "{com.messanger.auth.common.validation.AuthoritiesExists.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        AuthoritiesExist[] value();
    }
}
