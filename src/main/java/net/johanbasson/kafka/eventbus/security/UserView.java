package net.johanbasson.kafka.eventbus.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.johanbasson.kafka.eventbus.core.eventbus.EventHandler;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserView {

    private final UserRepository userRepository;

    @EventHandler
    public void handle(UserRegisteredEvent event) {
        userRepository.insert(event.toUser());
        log.info("Inserted User {}", event);
    }
}
