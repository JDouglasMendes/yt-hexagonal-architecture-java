package com.ms.order.adapters.controllers;

import com.ms.order.adapters.controllers.dtos.OrderDto;
import com.ms.order.adapters.controllers.dtos.OrderProductDto;
import com.ms.order.application.commands.OrderCommand;
import com.ms.order.application.exceptions.InvalidAmountProductException;
import com.ms.order.application.ports.OrderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    @Mock
    private OrderService orderService;

    @InjectMocks
    OrderController controller;

    OrderDto orderDto;
    OrderCommand orderCommand;
    @BeforeEach
    public void init() {
        orderDto = new OrderDto();
        orderDto.setIdCustomer(1);
        orderDto.setProducts(Arrays.asList(new OrderProductDto(1, 2)));

        orderCommand = new OrderCommand(1);
        orderCommand.AddProduct(1 , 2);
    }

    @Test
    public void addNewOrder_with_success() throws InvalidAmountProductException {
        // Act
        ResponseEntity response = controller.addOrder(orderDto);

        // Assert
        then(orderService).should().save(orderCommand);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
