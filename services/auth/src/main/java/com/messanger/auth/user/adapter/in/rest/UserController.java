package com.messanger.auth.user.adapter.in.rest;

import com.messanger.auth.common.exception.Exceptions;
import com.messanger.auth.user.adapter.mapper.UserMapper;
import com.messanger.auth.user.application.port.in.CreateUserUseCase;
import com.messanger.auth.user.application.port.in.DeleteUserUseCase;
import com.messanger.auth.user.application.port.in.GetUserByIdUseCase;
import com.messanger.auth.user.application.port.in.PageUsersUseCase;
import com.messanger.auth.user.domain.command.CreateUserCommand;
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
@RequestMapping("/api/users")
public class UserController {
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final PageUsersUseCase pageUsersUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UserMapper mapper;

    @GetMapping("/{id}")
    public AuthApi.User get(@PathVariable("id") String id) {
        return getUserByIdUseCase.get(id)
                .map(mapper::toUserDto)
                .orElseThrow(() ->
                        Exceptions.ELEMENT_NOT_FOUND.create()
                                .message("User with id ''{}'' not found.", id)
                                .get()
                );
    }

    @GetMapping
    public Page<AuthApi.User> get(@PageableDefault(sort = "username") Pageable pageable) {
        return pageUsersUseCase.page(pageable)
                .map(mapper::toUserDto);
    }

    @PostMapping
    public void create(@RequestBody @Validated CreateUserCommand command) {
        createUserUseCase.create(command);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        deleteUserUseCase.delete(id);
    }

}
