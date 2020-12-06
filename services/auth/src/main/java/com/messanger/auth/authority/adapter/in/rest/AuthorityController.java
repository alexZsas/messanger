package com.messanger.auth.authority.adapter.in.rest;

import com.messanger.auth.authority.adapter.mapper.AuthorityMapper;
import com.messanger.auth.authority.application.port.in.CreateAuthorityUseCase;
import com.messanger.auth.authority.application.port.in.DeleteAuthorityUseCase;
import com.messanger.auth.authority.application.port.in.GetAuthorityByIdUseCase;
import com.messanger.auth.authority.application.port.in.PageAuthoritiesUseCase;
import com.messanger.auth.authority.domain.command.CreateAuthorityCommand;
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
@RequestMapping("/api/authorities")
public class AuthorityController {
    private final GetAuthorityByIdUseCase getAuthorityByIdUseCase;
    private final PageAuthoritiesUseCase pageAuthoritiesUseCase;
    private final CreateAuthorityUseCase createAuthorityUseCase;
    private final DeleteAuthorityUseCase deleteAuthorityUseCase;
    private final AuthorityMapper mapper;

    @GetMapping
    public Page<AuthApi.Authority> get(@PageableDefault(sort = "id") Pageable pageable) {
        return pageAuthoritiesUseCase.page(pageable)
                .map(mapper::toAuthorityDto);
    }

    @GetMapping("/{id}")
    public AuthApi.Authority get(@PathVariable("id") String id) {
        return getAuthorityByIdUseCase.get(id)
                .map(mapper::toAuthorityDto)
                .orElseThrow(() ->
                        Exceptions.ELEMENT_NOT_FOUND.create()
                                .message("Authority with id ''{}'' not found.", id)
                                .get()
                );
    }

    @PostMapping
    public void create(@RequestBody @Validated CreateAuthorityCommand command) {
        createAuthorityUseCase.create(command);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        deleteAuthorityUseCase.delete(id);
    }

}
