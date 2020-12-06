package com.messanger.auth.authority.domain.command;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Validated
public class CreateAuthorityCommand {
    @NotBlank(message = "{authority.name.not-blank}")
    private String name;
}
