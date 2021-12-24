package com.ms.order.application.ports;

import com.ms.order.application.domain.Order;

import java.util.List;
import java.util.Set;

public interface OrderRepository {
    Order save(Order order);
    List<Order> getAllOrder(long idCustomer);
}
