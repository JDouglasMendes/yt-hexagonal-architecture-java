package com.ms.order.application.domain;

import com.ms.order.application.exceptions.InvalidAmountProductException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderTest {

    @Test
    public  void AddProduct_with_success() throws InvalidAmountProductException {
        // Arrange
        Order order = new Order(new Customer(1, "fake", "name"));
        // Act
        order.AddProducts(new Product(1, "fake product", 10, 1));
        // Assert
        Assertions.assertEquals(10, order.getTotal());
    }

    @Test
    public  void AddProduct_with_amount_equals_zero_expect_throws(){
        // Arrange
        Order order = new Order(new Customer(1, "fake", "name"));
        // Act
        Exception exception = assertThrows(InvalidAmountProductException.class, () -> {
            order.AddProducts(new Product(1, "fake product", 10, 0));
        });
        // Assert
        Assertions.assertEquals("amount is required", exception.getMessage());
    }
}
