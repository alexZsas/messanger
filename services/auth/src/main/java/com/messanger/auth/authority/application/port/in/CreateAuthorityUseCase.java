package com.messanger.auth.authority.application.port.in;

import com.messanger.auth.authority.domain.command.CreateAuthorityCommand;

public interface CreateAuthorityUseCase {
    void create(CreateAuthorityCommand command);
}
