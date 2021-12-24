package com.ms.order.adapters.controllers;

import com.ms.order.adapters.controllers.dtos.OrderDto;
import com.ms.order.application.commands.OrderCommand;
import com.ms.order.application.domain.Order;
import com.ms.order.application.exceptions.InvalidAmountProductException;
import com.ms.order.application.ports.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public ResponseEntity<Order> addOrder(@RequestBody @Valid OrderDto orderDto) throws InvalidAmountProductException {
        OrderCommand orderCommand = new OrderCommand(orderDto.getIdCustomer());
        orderDto.getProducts().forEach(x -> {
            orderCommand.AddProduct(x.getIdProduct(), x.getAmount());
        });
        return new ResponseEntity<>(orderService.save(orderCommand), HttpStatus.CREATED);
    }
}
