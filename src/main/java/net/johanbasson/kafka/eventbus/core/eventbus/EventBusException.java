package net.johanbasson.kafka.eventbus.core.eventbus;

public class EventBusException extends RuntimeException{
    public EventBusException(String message) {
        super(message);
    }
}
