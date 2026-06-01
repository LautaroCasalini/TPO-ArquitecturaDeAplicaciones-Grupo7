package com.uade.order.application.service;

import com.uade.order.domain.event.OrderCreatedEvent;
import com.uade.order.domain.model.Order;
import com.uade.order.domain.port.in.OrderUseCase;
import com.uade.order.domain.port.out.EventPublisherPort;
import com.uade.order.domain.port.out.OrderRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final EventPublisherPort eventPublisherPort;

    @Override
    public Order createOrder(Order order) {
        order.setStatus("CREATED");
        Order savedOrder = orderRepositoryPort.save(order);
        
        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getProductId(),
                savedOrder.getQuantity()
        );
        eventPublisherPort.publishOrderCreatedEvent(event);
        
        return savedOrder;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepositoryPort.findAll();
    }
}
