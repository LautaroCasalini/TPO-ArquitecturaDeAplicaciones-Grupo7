package com.uade.order.domain.port.out;

import com.uade.order.domain.model.Order;
import java.util.List;

public interface OrderRepositoryPort {
    Order save(Order order);
    List<Order> findAll();
}
