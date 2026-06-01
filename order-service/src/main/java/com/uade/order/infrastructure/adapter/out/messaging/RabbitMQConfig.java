package com.uade.order.infrastructure.adapter.out.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("rabbitmq")
public class RabbitMQConfig {

    public static final String EXCHANGE = "order.exchange";
    public static final String QUEUE = "order.created.queue";
    public static final String ROUTING_KEY = "order.created";
    
    // Para consumir de inventory
    public static final String INVENTORY_EXCHANGE = "inventory.exchange";
    public static final String INVENTORY_QUEUE = "order.product.created.queue";
    public static final String INVENTORY_ROUTING_KEY = "product.created";

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue orderCreatedQueue() {
        return QueueBuilder.durable(QUEUE).build();
    }

    @Bean
    public Binding orderCreatedBinding(Queue orderCreatedQueue, TopicExchange orderExchange) {
        return BindingBuilder.bind(orderCreatedQueue).to(orderExchange).with(ROUTING_KEY);
    }

    @Bean
    public Queue inventoryProductCreatedQueue() {
        return QueueBuilder.durable(INVENTORY_QUEUE).build();
    }

    @Bean
    public Binding inventoryProductCreatedBinding(Queue inventoryProductCreatedQueue) {
        return BindingBuilder.bind(inventoryProductCreatedQueue)
                .to(new TopicExchange(INVENTORY_EXCHANGE))
                .with(INVENTORY_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
