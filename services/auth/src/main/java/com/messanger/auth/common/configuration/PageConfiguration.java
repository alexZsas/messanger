package com.messanger.auth.common.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import java.io.IOException;

@Configuration
@EnableSpringDataWebSupport
public class PageConfiguration {

    @Bean
    public SimpleModule jacksonPageWithJsonViewModule() {
        SimpleModule module = new SimpleModule("jackson-page", Version.unknownVersion());
        module.addSerializer(Page.class, new PageSerializer());
        return module;
    }

    @SuppressWarnings("rawtypes")
    private static class PageSerializer extends StdSerializer<Page> {
        private static final long serialVersionUID = 8054860990027570409L;

        PageSerializer() {
            super(Page.class);
        }

        @Override
        public void serialize(Page value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeStartObject();

            gen.writeFieldName("content");
            provider.defaultSerializeValue(value.getContent(), gen);

            gen.writeNumberField("page", value.getNumber());
            gen.writeNumberField("size", value.getSize());
            gen.writeNumberField("totalElements", value.getTotalElements());
            gen.writeNumberField("totalPages", value.getTotalPages());
            gen.writeNumberField("numberOfElements", value.getNumberOfElements());

            gen.writeEndObject();
        }
    }
}
