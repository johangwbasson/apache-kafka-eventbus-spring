package net.johanbasson.kafka.eventbus.core.eventbus;

public interface EventDispatcher {

    void dispatch(Event event);
}
