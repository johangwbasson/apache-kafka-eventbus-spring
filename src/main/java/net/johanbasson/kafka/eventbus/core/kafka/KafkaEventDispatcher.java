package net.johanbasson.kafka.eventbus.core.kafka;

import lombok.AllArgsConstructor;
import net.johanbasson.kafka.eventbus.core.eventbus.Event;
import net.johanbasson.kafka.eventbus.core.eventbus.EventDispatcher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class KafkaEventDispatcher implements EventDispatcher {

    private final KafkaTemplate<String, net.johanbasson.kafka.eventbus.core.eventbus.Event> kafkaTemplate;

    @Override
    public void dispatch(Event event) {
        kafkaTemplate.send(KafkaTopics.EVENTS, event.getEventId().toString(), event);
    }
}
