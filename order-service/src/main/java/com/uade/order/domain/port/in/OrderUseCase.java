package com.uade.order.domain.port.in;

import com.uade.order.domain.model.Order;
import java.util.List;

public interface OrderUseCase {
    Order createOrder(Order order);
    List<Order> getAllOrders();
}
