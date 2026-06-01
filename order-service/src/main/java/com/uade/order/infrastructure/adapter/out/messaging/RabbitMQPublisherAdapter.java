package com.uade.order.infrastructure.adapter.out.messaging;

import com.uade.order.domain.port.out.EventPublisherPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("rabbitmq")
@RequiredArgsConstructor
public class RabbitMQPublisherAdapter implements EventPublisherPort {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publishOrderCreatedEvent(Object event) {
        log.info("Publicando evento OrderCreated en RabbitMQ: {}", event);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, event);
    }
}
