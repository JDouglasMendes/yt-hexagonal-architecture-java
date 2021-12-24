package com.ms.order.adapters.persistence.maps;

import com.ms.order.adapters.persistence.entities.OrderEntity;
import com.ms.order.adapters.persistence.entities.OrderItemEntity;
import com.ms.order.application.domain.Customer;
import com.ms.order.application.domain.Order;
import com.ms.order.application.domain.Product;
import com.ms.order.application.exceptions.InvalidAmountProductException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OrderMapTest {
    Order order;
    OrderEntity orderEntity;

    @BeforeEach
    public  void initTest() throws InvalidAmountProductException {
        order = new Order(new Customer(1, "firstname", "lastName"));
        order.AddProducts(new Product(1,"name", 10,1));

        orderEntity = new OrderEntity();
        orderEntity.setIdCustomer(1);
        orderEntity.setCustomerFirstName("firstname");
        orderEntity.setCustomerLastName("lastName");
        orderEntity.setOrderItemSet(
                Set.of(new OrderItemEntity(1,"name", 10, 1))
        );
    }

    @Test
    public void toEntity_with_success()  {
        OrderEntity result = new OrderMap().ToEntity(order);
        assertThat(result).isEqualTo(orderEntity);
    }

    @Test
    public void toDomain_with_success() throws InvalidAmountProductException {
        Order result = new OrderMap().ToDomain(orderEntity);
        assertThat(order).isEqualTo(result);
    }
}
