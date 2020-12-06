package com.messanger.common.helper.exception;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;

public class SimpleExceptionCreator<E extends Exception> implements ExceptionCreator<E> {
    private final Constructor<E> constructor;

    private String message;
    private Object[] args;
    private Throwable cause;

    @SneakyThrows
    public SimpleExceptionCreator(Class<E> exception) {
        this.constructor = accessibleConstructor(exception, String.class, Throwable.class);
    }

    @Override
    public ExceptionCreator<E> message(String message) {
        this.message = message;

        return this;
    }

    @Override
    public ExceptionCreator<E> message(String message, Object... args) {
        this.message = message;
        this.args = args;

        return this;
    }

    @Override
    public ExceptionCreator<E> args(Object... args) {
        this.args = args;
        return this;
    }

    @Override
    public ExceptionCreator<E> cause(Throwable cause) {
        this.cause = cause;

        return this;
    }

    @Override
    @SneakyThrows
    public E get() {
        String message = MessageFormatter.format(this.message, this.args);

        return constructor.newInstance(message, this.cause);
    }
    
    @SneakyThrows
    protected Constructor<E> accessibleConstructor(Class<E> type, Class<?>... params) {
        Constructor<E> c = type.getDeclaredConstructor(params);
        c.setAccessible(true);
        return c;
    }

    public static <E extends RuntimeException> SimpleExceptionCreator<E> create(Class<E> target) {
        return new SimpleExceptionCreator<>(target);
    }
}
