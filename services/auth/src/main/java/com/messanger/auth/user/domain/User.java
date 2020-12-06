package com.messanger.auth.user.domain;

import com.messanger.auth.authority.domain.Authority;
import com.messanger.auth.user.domain.command.CreateUserCommand;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    private String id;
    private String username;
    private String password;
    private boolean expired;
    private boolean locked;
    private boolean enabled;
    private Collection<Authority> authorities;

    public User(CreateUserCommand command) {
        this.id = UUID.randomUUID().toString();
        this.username = command.getUsername();
        this.password = command.getPassword();
        this.enabled = true;
        this.authorities = command.getAuthorities();
    }

    public UserDetails toUserDetails() {
        return new org.springframework.security.core.userdetails.User(
                username,
                password,
                enabled,
                !expired,
                !expired,
                !locked,
                authorities
        );
    }
}
