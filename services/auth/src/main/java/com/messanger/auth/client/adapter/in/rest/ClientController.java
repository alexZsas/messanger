package com.messanger.auth.client.adapter.in.rest;

import com.messanger.auth.client.adapter.mapper.ClientMapper;
import com.messanger.auth.client.application.port.in.CreateClientUseCase;
import com.messanger.auth.client.application.port.in.DeleteClientUseCase;
import com.messanger.auth.client.application.port.in.GetClientByIdUseCase;
import com.messanger.auth.client.application.port.in.PageClientsUseCase;
import com.messanger.auth.client.domain.command.CreateClientCommand;
import com.messanger.auth.common.exception.Exceptions;
import com.messanger.common.api.authapi.AuthApi;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientController {
    private final CreateClientUseCase createClientUseCase;
    private final PageClientsUseCase pageClientsUseCase;
    private final GetClientByIdUseCase getClientByIdUseCase;
    private final DeleteClientUseCase deleteClientUseCase;
    private final ClientMapper mapper;

    @GetMapping("/{id}")
    public AuthApi.Client get(@PathVariable("id") String id) {
        return getClientByIdUseCase.get(id)
                .map(mapper::toClientDto)
                .orElseThrow(() ->
                        Exceptions.ELEMENT_NOT_FOUND.create()
                                .message("Client with id ''{}'' not found.", id)
                                .get()
                );
    }

    @GetMapping
    public Page<AuthApi.Client> get(@PageableDefault(sort = "id") Pageable pageable) {
        return pageClientsUseCase.page(pageable)
                .map(mapper::toClientDto);
    }

    @PostMapping
    public void create(@RequestBody @Validated CreateClientCommand command) {
        createClientUseCase.create(command);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        deleteClientUseCase.delete(id);
    }
}
