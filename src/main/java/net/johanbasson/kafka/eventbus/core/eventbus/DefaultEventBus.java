package net.johanbasson.kafka.eventbus.core.eventbus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class DefaultEventBus implements EventBus {

    private final Map<Class<?>, Set<HandlerMetaData>> handlerMapping = new HashMap<>();

    private record HandlerMetaData(
            Object object,
            Method method) {
    }

    public void register(Object obj) {
        for (Method method : obj.getClass().getMethods()) {
            if (hasHandlerAnnotation(method) && method.getParameterCount() == 1) {
                Class<?> clazz = method.getParameters()[0].getType();
                Set<HandlerMetaData> handlers = handlerMapping.computeIfAbsent(clazz, c -> new HashSet<>());
                handlers.add(new HandlerMetaData(obj, method));
                handlerMapping.remove(clazz);
                handlerMapping.put(clazz, handlers);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void publish(Event event) {
        Set<HandlerMetaData> handlers = handlerMapping.get(event.getClass());
        if (handlers == null) {
            throw new EventBusException("There is no handlers registered for " + event.getClass().getName());
        }
        handlers.forEach(md -> {
            try {
                md.method.invoke(md.object, event);
            } catch (Exception ex) {
                log.error("Error invoking event. Class: {}, Method: {}", md.object.getClass().getName(), md.method.getName());
            }
        });
    }

    private boolean hasHandlerAnnotation(Method method) {
        return method.getAnnotation(EventHandler.class) != null;
    }

}
