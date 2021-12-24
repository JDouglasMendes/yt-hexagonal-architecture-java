package com.ms.order.application.service;

import com.ms.order.application.commands.OrderCommand;
import com.ms.order.application.domain.Customer;
import com.ms.order.application.domain.Order;
import com.ms.order.application.domain.Product;
import com.ms.order.application.exceptions.CustomerNotFoundException;
import com.ms.order.application.exceptions.InvalidAmountProductException;
import com.ms.order.application.exceptions.ProductNotFoundException;
import com.ms.order.application.ports.CustomerService;
import com.ms.order.application.ports.OrderRepository;
import com.ms.order.application.ports.ProductService;
import com.ms.order.application.services.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

public class OrderServiceImplTest {
    private OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
    private CustomerService customerService = Mockito.mock(CustomerService.class);
    private ProductService productService = Mockito.mock(ProductService.class);

    OrderCommand command;
    Customer customer;
    Product product;
    @BeforeEach
    public void  initCommand() throws InvalidAmountProductException {
        customer = new Customer(1L, "first", "last");
        product = new Product(1L,"name", 10.0, 1);
        command = new OrderCommand(1);
        command.AddProduct(1,2);
    }

    @Test
    public void save_with_success() throws InvalidAmountProductException {
        // Arrange
        given(customerService.getCustomer(eq(1L)))
                .willReturn(Optional.of(customer));

        given(productService.getProducts(eq(Arrays.asList(1L))))
                .willReturn(Optional.of(Arrays.asList(product)));

        Order order = new Order(customer);
        order.AddProducts(product);

        given(orderRepository.save(eq(order))).willReturn(order);

        OrderServiceImpl service = new OrderServiceImpl(orderRepository, customerService, productService);

        // Act
        Order response = service.save(command);

        // Assert
        then(orderRepository).should().save(order);
        assertThat(response).isEqualTo(order);
    }

    @Test
    public void save_with_error_customer_not_found() throws InvalidAmountProductException {
        // Arrange
        given(customerService.getCustomer(eq(1L)))
                .willReturn(Optional.ofNullable(null));

        OrderCommand command = new OrderCommand(2L);
        command.AddProduct(1,2);
        Order order = new Order(customer);
        order.AddProducts(product);

        OrderServiceImpl service = new OrderServiceImpl(orderRepository, customerService, productService);

        // Act
        RuntimeException exception = assertThrows(CustomerNotFoundException.class, () -> {
            service.save(command);
        });

        // Assert
        then(orderRepository).shouldHaveNoInteractions();
        Assertions.assertEquals("The customer id 2 not found", exception.getMessage());
    }

    @Test
    public void save_with_error_product_not_found() throws InvalidAmountProductException {
        // Arrange
        given(customerService.getCustomer(eq(1L)))
                .willReturn(Optional.of(customer));

        given(productService.getProducts(eq(Arrays.asList(1L))))
                .willReturn(Optional.of(Arrays.asList(product)));

        OrderCommand command = new OrderCommand(1L);
        command.AddProduct(2,2);
        Order order = new Order(customer);
        order.AddProducts(product);

        OrderServiceImpl service = new OrderServiceImpl(orderRepository, customerService, productService);

        // Act
        RuntimeException exception = assertThrows(ProductNotFoundException.class, () -> {
            service.save(command);
        });

        // Assert
        then(orderRepository).shouldHaveNoInteractions();
        Assertions.assertEquals("Products not found", exception.getMessage());
    }

}
