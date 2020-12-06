package com.messanger.auth.user.adapter.in.yaml;

import com.messanger.auth.authority.application.port.in.ListAuthoritiesByNamesUseCase;
import com.messanger.auth.authority.domain.Authority;
import com.messanger.auth.client.adapter.in.yaml.ClientYamlInitializer;
import com.messanger.auth.common.initializer.Initializer;
import com.messanger.auth.user.adapter.in.yaml.properties.UserInitializeProperties;
import com.messanger.auth.user.adapter.mapper.UserMapper;
import com.messanger.auth.user.application.port.in.CreateUserUseCase;
import com.messanger.auth.user.application.port.in.ListUsersByIdsUseCase;
import com.messanger.auth.user.application.port.in.ListUsersByUsernamesUseCase;
import com.messanger.auth.user.domain.User;
import com.messanger.auth.user.domain.command.CreateUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserYamlInitializer implements Initializer {
    public static final int ORDER = ClientYamlInitializer.ORDER;

    private final UserMapper userMapper;
    private final UserInitializeProperties userInitializeProperties;
    private final ListUsersByUsernamesUseCase listUsersByUsernamesUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final ListAuthoritiesByNamesUseCase listAuthoritiesByNamesUseCase;

    @Override
    public void init() {
        Collection<UserInitializeProperties.UserInfo> userInfos = userInitializeProperties.getUserInfos();

        if (!CollectionUtils.isEmpty(userInfos)) {
            Collection<String> userNames = userInfos.stream()
                    .map(UserInitializeProperties.UserInfo::getUsername)
                    .collect(Collectors.toList());

            Set<String> existingUsernames = listUsersByUsernamesUseCase.list(userNames).stream()
                    .map(User::getUsername)
                    .collect(Collectors.toSet());

            for (UserInitializeProperties.UserInfo userInfo : userInfos) {
                if (!existingUsernames.contains(userInfo.getUsername())) {
                    CreateUserCommand command = userMapper.mapToCommand(userInfo);
                    fillAuthorities(command);
                    createUserUseCase.create(command);
                }
            }
        }
    }

    private void fillAuthorities(CreateUserCommand command) {
        List<String> authorityNames = command.getAuthorities().stream()
                .map(Authority::getAuthority)
                .collect(Collectors.toList());
        command.setAuthorities(listAuthoritiesByNamesUseCase.get(authorityNames));
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
