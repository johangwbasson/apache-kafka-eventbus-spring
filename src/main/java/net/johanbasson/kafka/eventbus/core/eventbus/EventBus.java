package net.johanbasson.kafka.eventbus.core.eventbus;

public interface EventBus {

    void publish(Event event);
}
