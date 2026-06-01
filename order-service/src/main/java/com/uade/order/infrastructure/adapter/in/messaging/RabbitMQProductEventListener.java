package com.uade.order.infrastructure.adapter.in.messaging;

import com.uade.order.infrastructure.adapter.out.messaging.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("rabbitmq")
public class RabbitMQProductEventListener {

    @RabbitListener(queues = RabbitMQConfig.INVENTORY_QUEUE)
    public void handleProductCreated(ProductCreatedEvent event) {
        log.info("Recibido evento ProductCreated desde RabbitMQ: {}", event);
        // Aquí podríamos actualizar un cache local de productos si fuera necesario
    }
}
