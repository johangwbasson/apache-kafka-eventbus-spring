package net.johanbasson.kafka.eventbus.security;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);

    void insert(User user);
}
