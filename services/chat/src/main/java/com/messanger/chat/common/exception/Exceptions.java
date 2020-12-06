package com.messanger.chat.common.exception;

import com.messanger.common.helper.exception.BaseException;
import com.messanger.common.helper.exception.ExceptionCreator;
import com.messanger.common.helper.exception.ExceptionCreatorFactory;
import com.messanger.common.helper.exception.SimpleExceptionCreator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Exceptions implements ExceptionCreatorFactory<ExceptionCreator<? extends BaseException>> {
    CHAT_NOT_FOUND(ChatNotFoundException.class)
    ;
    
    private final Class<? extends BaseException> target;

    @Override
    public ExceptionCreator<? extends BaseException> create() {
        return SimpleExceptionCreator.create(target);
    }
}
