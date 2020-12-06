package com.messanger.auth.user.domain.command;

import com.messanger.auth.authority.domain.Authority;
import com.messanger.auth.common.validation.constraint.annotation.AuthoritiesExist;
import com.messanger.auth.common.validation.constraint.annotation.PasswordAndConfirmationEqual;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@PasswordAndConfirmationEqual
public class CreateUserCommand {
    @NotBlank(message = "{user.username.not-blank}")
    private String username;
    @NotBlank(message = "{user.password.not-blank}")
    @Pattern(
            message = "{user.password.pattern}",
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"
    )
    private String password;
    @NotBlank(message = "{user.password.not-blank}")
    @Pattern(
            message = "{user.password.pattern}",
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"
    )
    private String confirmPassword;
    @AuthoritiesExist
    private Collection<Authority> authorities;
}
