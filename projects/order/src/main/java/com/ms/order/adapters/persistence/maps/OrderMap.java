package com.ms.order.adapters.persistence.maps;

import com.ms.order.adapters.persistence.entities.OrderEntity;
import com.ms.order.adapters.persistence.entities.OrderItemEntity;
import com.ms.order.application.domain.Customer;
import com.ms.order.application.domain.Order;
import com.ms.order.application.domain.Product;
import com.ms.order.application.exceptions.InvalidAmountProductException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMap {
    public OrderEntity ToEntity(Order order) {
        return OrderEntity.builder()
                .idCustomer(order.getCustomer().getId())
                .customerFirstName(order.getCustomer().getFirstName())
                .customerLastName(order.getCustomer().getLastName())
                .orderItemSet(order.getProducts()
                        .stream().map(product -> new OrderItemEntity(product.getId(),
                                product.getName(),
                                product.getValue(),
                                product.getAmount()
                                ))
                        .collect(Collectors.toSet()))
                .build();
    }

    public Order ToDomain(OrderEntity orderEntity) throws InvalidAmountProductException {
        Order order = new Order(new Customer(orderEntity.getIdCustomer(),
                                             orderEntity.getCustomerFirstName(),
                                             orderEntity.getCustomerLastName()));
        for (OrderItemEntity orderItemEntity : orderEntity.getOrderItemSet()) {
            order.AddProducts(new Product(orderItemEntity.getIdProduct(),
                    orderItemEntity.getDescriptionProduct(),
                    orderItemEntity.getValue(),
                    orderItemEntity.getAmount()));
        }
        return order;
    }
}
