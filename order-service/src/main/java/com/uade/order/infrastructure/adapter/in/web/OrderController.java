package com.uade.order.infrastructure.adapter.in.web;

import com.uade.order.domain.model.Order;
import com.uade.order.domain.port.in.OrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderUseCase orderUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody Order order) {
        return orderUseCase.createOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderUseCase.getAllOrders();
    }
}
