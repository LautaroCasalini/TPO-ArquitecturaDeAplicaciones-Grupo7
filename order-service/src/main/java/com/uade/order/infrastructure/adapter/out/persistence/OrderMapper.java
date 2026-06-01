package com.uade.order.infrastructure.adapter.out.persistence;

import com.uade.order.domain.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderJpaEntity toEntity(Order order) {
        return OrderJpaEntity.builder()
                .id(order.getId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .customerName(order.getCustomerName())
                .status(order.getStatus())
                .build();
    }

    public Order toDomain(OrderJpaEntity entity) {
        return Order.builder()
                .id(entity.getId())
                .productId(entity.getProductId())
                .quantity(entity.getQuantity())
                .customerName(entity.getCustomerName())
                .status(entity.getStatus())
                .build();
    }
}
