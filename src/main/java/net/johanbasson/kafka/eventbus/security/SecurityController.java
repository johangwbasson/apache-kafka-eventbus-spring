package net.johanbasson.kafka.eventbus.security;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class SecurityController {

    private final UserService userService;

    @PostMapping("/api/v1/users")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterUserCommand command) {
        userService.register(command);
        return ResponseEntity.accepted().build();
    }
}
