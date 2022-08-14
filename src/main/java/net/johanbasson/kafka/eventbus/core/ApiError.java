package net.johanbasson.kafka.eventbus.core;

public class ApiError extends RuntimeException {
    public ApiError(String message) {
        super(message);
    }
}
