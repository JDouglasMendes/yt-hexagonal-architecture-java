package com.ms.order.adapters.persistence;

import com.ms.order.adapters.persistence.entities.OrderEntity;
import com.ms.order.adapters.persistence.maps.OrderMap;
import com.ms.order.application.domain.Order;
import com.ms.order.application.exceptions.InvalidAmountProductException;
import com.ms.order.application.ports.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

public class OrderRepositoryImpl implements OrderRepository {
    private OrderRepositoryJPA repositoryJPA;
    private OrderMap orderMap;

    public OrderRepositoryImpl(OrderRepositoryJPA repositoryJPA, OrderMap orderMap) {
        this.repositoryJPA = repositoryJPA;
        this.orderMap = orderMap;
    }

    @Override
    public Order save(Order order) {
        repositoryJPA.save(orderMap.ToEntity(order));
        return order;
    }

    @Override
    public List<Order> getAllOrder(long idCustomer) {
        List<OrderEntity> list = repositoryJPA.findByIdCustomer(idCustomer);
        return  list.stream().map(orderEntity -> {
            try {
                return orderMap.ToDomain(orderEntity);
            } catch (InvalidAmountProductException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }
}
