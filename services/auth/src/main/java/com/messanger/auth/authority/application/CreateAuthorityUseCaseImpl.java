package com.messanger.auth.authority.application;

import com.messanger.auth.authority.application.port.in.CreateAuthorityUseCase;
import com.messanger.auth.authority.application.port.out.CreateAuthorityPort;
import com.messanger.auth.authority.application.port.out.ExistsAuthorityByNamePort;
import com.messanger.auth.authority.application.port.out.SendAuthorityCreatedEventPort;
import com.messanger.auth.authority.domain.Authority;
import com.messanger.auth.authority.domain.command.CreateAuthorityCommand;
import com.messanger.auth.common.exception.Exceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateAuthorityUseCaseImpl implements CreateAuthorityUseCase {
    private final ExistsAuthorityByNamePort existsAuthorityByNamePort;
    private final CreateAuthorityPort createAuthorityPort;
    private final SendAuthorityCreatedEventPort sendAuthorityCreatedEventPort;

    @Override
    public void create(CreateAuthorityCommand command) {
        if (existsAuthorityByNamePort.exists(command.getName())) {
            Exceptions.ELEMENT_ALREADY_EXISTS.create()
                    .message("Authority with name ''{}'' already exists.", command.getName())
                    .doThrow();
        }

        Authority authority = new Authority(command);
        createAuthorityPort.create(authority);
        sendAuthorityCreatedEventPort.send(authority);
    }
}
