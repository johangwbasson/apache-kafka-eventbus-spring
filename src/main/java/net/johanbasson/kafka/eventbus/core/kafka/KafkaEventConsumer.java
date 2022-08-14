package net.johanbasson.kafka.eventbus.core.kafka;

import lombok.AllArgsConstructor;
import net.johanbasson.kafka.eventbus.core.eventbus.Event;
import net.johanbasson.kafka.eventbus.core.eventbus.EventBus;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class KafkaEventConsumer {

    private final EventBus eventBus;

    @KafkaListener(topics = KafkaTopics.EVENTS, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, Event> payload) {
        eventBus.publish(payload.value());
    }
}
