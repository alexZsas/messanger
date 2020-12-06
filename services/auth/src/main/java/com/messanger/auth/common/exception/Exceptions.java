package com.messanger.auth.common.exception;

import com.messanger.common.helper.exception.BaseException;
import com.messanger.common.helper.exception.ExceptionCreator;
import com.messanger.common.helper.exception.ExceptionCreatorFactory;
import com.messanger.common.helper.exception.SimpleExceptionCreator;

public enum Exceptions implements ExceptionCreatorFactory<ExceptionCreator<? extends BaseException>> {
    CONFIRM_PASSWORD_NOT_MATCHED(ConfirmPasswordNotMatchedException.class),
    EMPTY_PASSWORD(EmptyPasswordException.class),
    ILLEGAL_PASSWORD(IllegalPasswordException.class),
    ELEMENT_NOT_FOUND(ElementNotFoundException.class),
    ELEMENT_ALREADY_EXISTS(ElementAlreadyExistsException.class),
    ;

    private final Class<? extends BaseException> target;

    Exceptions(Class<? extends BaseException> target) {
        this.target = target;
    }


    @Override
    public ExceptionCreator<? extends BaseException> create() {
        return SimpleExceptionCreator.create(target);
    }
}
