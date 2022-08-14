package net.johanbasson.kafka.eventbus.core.eventbus;

import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class Event {

    private final UUID eventId = UUID.randomUUID();
}
