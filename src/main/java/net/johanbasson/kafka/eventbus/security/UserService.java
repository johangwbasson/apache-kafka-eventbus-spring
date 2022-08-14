package net.johanbasson.kafka.eventbus.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.johanbasson.kafka.eventbus.core.ApiError;
import net.johanbasson.kafka.eventbus.core.eventbus.EventDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private final EventDispatcher eventDispatcher;


    public void register(RegisterUserCommand command) {
        Optional<User> maybeUser = userRepository.findByEmail(command.getEmail());
        if (maybeUser.isPresent()) {
            throw new ApiError("User already exists");
        }

        log.info("Command : {}", command);
        UserRegisteredEvent event = new UserRegisteredEvent(UUID.randomUUID(), command.getEmail(), command.getPassword(), LocalDateTime.now());
        eventDispatcher.dispatch(event);
        log.info("Dispatched UserRegisteredEvent: {}", event);
    }
}
