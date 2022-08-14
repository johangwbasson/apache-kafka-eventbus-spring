package net.johanbasson.kafka.eventbus;

import net.johanbasson.kafka.eventbus.core.eventbus.DefaultEventBus;
import net.johanbasson.kafka.eventbus.core.eventbus.EventBus;
import net.johanbasson.kafka.eventbus.core.ExceptionTranslator;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
public class KafkaEventbusConfiguration {

    @Bean
    public EventBus eventBus(ApplicationContext context) {
        DefaultEventBus eventBus = new DefaultEventBus();
        context.getBeansWithAnnotation(Service.class)
                .forEach((s, o) -> eventBus.register(o));
        return eventBus;
    }

    @Bean
    public ExceptionTranslator exceptionTransformer() {
        return new ExceptionTranslator();
    }

    @Bean
    public DefaultDSLContext dsl(DefaultConfiguration configuration) {
        return new DefaultDSLContext(configuration);
    }

    @Bean
    public DefaultConfiguration configuration(DataSourceConnectionProvider provider) {
        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
        jooqConfiguration.set(provider);
        jooqConfiguration.set(new DefaultExecuteListenerProvider(exceptionTransformer()));
        jooqConfiguration.set(SQLDialect.H2);

        return jooqConfiguration;
    }
}
