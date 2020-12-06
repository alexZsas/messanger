package com.messanger.common.helper.exception;

public interface ExceptionCreatorFactory<T extends ExceptionCreator<? extends Exception>> {
    T create();
}
