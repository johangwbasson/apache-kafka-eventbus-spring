package net.johanbasson.kafka.eventbus.security;

import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static net.johanbasson.kafka.support.db.tables.Users.USERS;

@Component
@AllArgsConstructor
public class JooqUserRepository implements UserRepository {

    private final DSLContext dslContext;

    @Override
    public Optional<User> findByEmail(String email) {
        return dslContext.selectFrom(USERS)
                .where(USERS.EMAIL.eq(email))
                .fetchOptional(rec -> new User(rec.getId(), rec.getEmail(), rec.getPassword(), rec.getCreated()));
    }

    @Override
    public void insert(User user) {
        dslContext.insertInto(USERS)
                .set(USERS.ID, user.getId())
                .set(USERS.EMAIL, user.getEmail())
                .set(USERS.PASSWORD, user.getPassword())
                .set(USERS.CREATED, user.getCreated())
                .execute();
    }
}
