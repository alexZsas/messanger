package com.messanger.auth.common.validation.constraint;

import com.messanger.auth.common.validation.constraint.annotation.PasswordAndConfirmationEqual;
import com.messanger.common.helper.exception.SimpleExceptionCreator;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.util.Assert;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.beans.PropertyDescriptor;
import java.util.Objects;

public class PasswordAndConfirmationEqualConstraintValidator
        implements ConstraintValidator<PasswordAndConfirmationEqual, Object> {
    private String passwordFieldName;
    private String passwordConfirmationFieldName;

    @Override
    public void initialize(PasswordAndConfirmationEqual constraintAnnotation) {
        this.passwordFieldName = constraintAnnotation.passwordField();
        this.passwordConfirmationFieldName = constraintAnnotation.passwordConfirmationField();

        Assert.notNull(this.passwordFieldName, () -> "Password field name cannot be null.");
        Assert.notNull(this.passwordConfirmationFieldName, () -> "Password confirmation field name cannot be null.");
        Assert.isTrue(
                !Objects.equals(this.passwordFieldName, this.passwordConfirmationFieldName),
                () -> "Password and password confirmation field names cannot be equal."
        );
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        BeanWrapper wrapper = new BeanWrapperImpl(value);
        checkProperty(wrapper, passwordFieldName);
        checkProperty(wrapper, passwordConfirmationFieldName);

        String password = (String) wrapper.getPropertyValue(passwordFieldName);
        String passwordConfirmation = (String) wrapper.getPropertyValue(passwordConfirmationFieldName);

        if (password == null || passwordConfirmation == null) {
            return true;
        }

        return Objects.equals(password, passwordConfirmation);
    }

    private void checkProperty(BeanWrapper beanWrapper, String propertyName) {
        try {
            PropertyDescriptor propertyDescriptor = beanWrapper.getPropertyDescriptor(propertyName);

            if (String.class.isAssignableFrom(propertyDescriptor.getPropertyType())) {
                SimpleExceptionCreator.create(IllegalArgumentException.class)
                        .message("Property ''{}'' of {} has a type other then java.lang.String")
                        .args(propertyName, beanWrapper.getWrappedClass().getName())
                        .doThrow();
            }
        } catch (InvalidPropertyException e) {
            SimpleExceptionCreator.create(IllegalArgumentException.class)
                    .message("''{}'' does not contain property named ''{}''")
                    .args(beanWrapper.getWrappedClass().getName(), propertyName)
                    .doThrow();
        }
    }
}
