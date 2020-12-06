package com.messanger.auth.authority.domain;

import com.messanger.auth.authority.domain.command.CreateAuthorityCommand;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Data
public class Authority implements GrantedAuthority {
    private static final long serialVersionUID = 6215094590461175314L;

    private final String id;
    private final String name;

    public Authority(CreateAuthorityCommand command) {
        this(UUID.randomUUID().toString(), command.getName());
    }

    public Authority(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
