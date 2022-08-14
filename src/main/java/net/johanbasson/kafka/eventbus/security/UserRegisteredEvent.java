package net.johanbasson.kafka.eventbus.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.johanbasson.kafka.eventbus.core.eventbus.Event;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisteredEvent extends Event {
    private UUID id;
    private String email;
    private String password;
    private LocalDateTime created;

    public User toUser() {
        return new User(id, email, password, created);
    }
}
