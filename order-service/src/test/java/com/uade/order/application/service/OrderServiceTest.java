package com.uade.order.application.service;

import com.uade.order.domain.event.OrderCreatedEvent;
import com.uade.order.domain.model.Order;
import com.uade.order.domain.port.out.EventPublisherPort;
import com.uade.order.domain.port.out.OrderRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepositoryPort orderRepositoryPort;

    @Mock
    private EventPublisherPort eventPublisherPort;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrder_ShouldSaveOrderAndPublishEvent() {
        // Arrange
        Order inputOrder = Order.builder()
                .productId(1L)
                .quantity(10)
                .customerName("Juan Perez")
                .build();

        Order savedOrder = Order.builder()
                .id(1L)
                .productId(1L)
                .quantity(10)
                .customerName("Juan Perez")
                .status("CREATED")
                .build();

        when(orderRepositoryPort.save(any(Order.class))).thenReturn(savedOrder);

        // Act
        Order result = orderService.createOrder(inputOrder);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("CREATED", result.getStatus());
        
        verify(orderRepositoryPort, times(1)).save(inputOrder);
        verify(eventPublisherPort, times(1)).publishOrderCreatedEvent(any(OrderCreatedEvent.class));
    }

    @Test
    void getAllOrders_ShouldReturnList() {
        // Arrange
        List<Order> orders = Arrays.asList(
                Order.builder().id(1L).build(),
                Order.builder().id(2L).build()
        );
        when(orderRepositoryPort.findAll()).thenReturn(orders);

        // Act
        List<Order> result = orderService.getAllOrders();

        // Assert
        assertEquals(2, result.size());
        verify(orderRepositoryPort, times(1)).findAll();
    }
}
