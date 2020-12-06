package com.messanger.auth.common.initializer;

import lombok.RequiredArgsConstructor;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@RequiredArgsConstructor
public class InitializerExecutor implements SmartLifecycle {
    private boolean autoStartup = false;
    private final Collection<Initializer> initializers;

    @Override
    @Transactional
    public void start() {
        autoStartup = true;
        for (Initializer initializer : initializers) {
            initializer.init();
        }
        autoStartup = false;
    }

    @Override
    public void stop() { }

    @Override
    public boolean isRunning() {
        return autoStartup;
    }
}
