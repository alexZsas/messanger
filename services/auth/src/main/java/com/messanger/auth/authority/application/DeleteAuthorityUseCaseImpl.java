package com.messanger.auth.authority.application;

import com.messanger.auth.authority.application.port.in.DeleteAuthorityUseCase;
import com.messanger.auth.authority.application.port.out.ExistsAuthorityByIdPort;
import com.messanger.auth.authority.application.port.out.DeleteAuthorityPort;
import com.messanger.auth.authority.application.port.out.SendAuthorityDeletedEventPort;
import com.messanger.auth.common.exception.Exceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteAuthorityUseCaseImpl implements DeleteAuthorityUseCase {
    private final ExistsAuthorityByIdPort existsAuthorityByIdPort;
    private final DeleteAuthorityPort deleteAuthorityPort;
    private final SendAuthorityDeletedEventPort sendAuthorityDeletedEventPort;

    @Override
    public void delete(String id) {
        if (!existsAuthorityByIdPort.exists(id)) {
            Exceptions.ELEMENT_NOT_FOUND.create()
                    .message("Authority with id ''{}'' not found.", id)
                    .doThrow();
        }
        deleteAuthorityPort.delete(id);
        sendAuthorityDeletedEventPort.send(id);
    }
}
