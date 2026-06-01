package com.uade.order.infrastructure.adapter.in.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreatedEvent implements Serializable {
    private Long productId;
    private String name;
    private Integer quantity;
    private Double price;
    private Instant timestamp;
}
