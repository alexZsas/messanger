package com.messanger.common.api.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public final class Events {
    public static final String MESSAGE_KEY = "kafka_messageKey";

    private Events() {
        throw new UnsupportedOperationException();
    }

    public static Map<String, Object> headersWithKey(BaseEvent<?> event) {
        return headersWithKey(event.getMetadata().getId());
    }

    public static Map<String, Object> headersWithKey(String value) {
        return Map.of(MESSAGE_KEY, value.getBytes());
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class Metadata {
        private String id = UUID.randomUUID().toString();
        private LocalDateTime date = LocalDateTime.now();
        private String version = "1.0";

        public static Metadata createDefault() {
            return new Metadata();
        }
    }

    @Data
    @SuperBuilder
    @Accessors(chain = true)
    public static abstract class BaseEvent<T> {
        private Metadata metadata;
        private T payload;
    }
}
