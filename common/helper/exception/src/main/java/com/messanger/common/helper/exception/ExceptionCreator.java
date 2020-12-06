package com.messanger.common.helper.exception;

public interface ExceptionCreator<E extends Exception> {
    ExceptionCreator<E> message(String message);

    ExceptionCreator<E> message(String message, Object... args);

    ExceptionCreator<E> args(Object... args);

    ExceptionCreator<E> cause(Throwable cause);

    default void doThrow() throws E {
        throw get();
    }

    E get();
}
