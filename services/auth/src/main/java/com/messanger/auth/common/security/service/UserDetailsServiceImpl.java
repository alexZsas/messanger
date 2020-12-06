package com.messanger.auth.common.security.service;

import com.messanger.auth.user.application.port.out.GetUserByUsernamePort;
import com.messanger.auth.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final GetUserByUsernamePort getUserByUsernamePort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsernamePort.get(username)
                .map(User::toUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User with username '" + username + "' not found."));
    }
}
