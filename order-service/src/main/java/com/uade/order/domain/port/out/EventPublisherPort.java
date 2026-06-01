package com.uade.order.domain.port.out;

public interface EventPublisherPort {
    void publishOrderCreatedEvent(Object event);
}
