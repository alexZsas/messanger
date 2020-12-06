package com.messanger.auth.common.initializer;

import org.springframework.core.Ordered;

public interface Initializer extends Ordered {
    void init();
}
