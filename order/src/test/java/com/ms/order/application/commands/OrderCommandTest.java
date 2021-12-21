package com.ms.order.application.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OrderCommandTest {
    @Test
    public void getProductsId_with_success(){
        // Arrange
        OrderCommand command = new OrderCommand(1);
        command.AddProduct(1, 2);
        // Act
        List<Long> ids = command.getProductsId();
        // Assert
        Assertions.assertEquals(1, ids.size());
        Assertions.assertEquals(1, ids.get(0));
    }
    @Test
    public void getAmountByProduct_with_success(){
        // Arrange
        OrderCommand command = new OrderCommand(1);
        command.AddProduct(1, 2);
        // Act
        Integer amount  = command.getAmountByProduct(1L);
        // Arrange
        Assertions.assertEquals(2, amount);
    }

    @Test
    public void getAmountByProduct_with_error_not_found_product(){
        // Arrange
        OrderCommand command = new OrderCommand(1);
        command.AddProduct(1, 2);
        // Act
        Integer amount  = command.getAmountByProduct(2L);
        // Arrange
        Assertions.assertEquals(0, amount);
    }
}
