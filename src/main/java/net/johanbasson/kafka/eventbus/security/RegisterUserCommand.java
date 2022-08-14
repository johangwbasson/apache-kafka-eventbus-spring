package net.johanbasson.kafka.eventbus.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserCommand {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
