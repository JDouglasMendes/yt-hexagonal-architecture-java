package com.ms.order.application.ports;

import com.ms.order.application.commands.OrderCommand;
import com.ms.order.application.domain.Order;
import com.ms.order.application.exceptions.InvalidAmountProductException;

import java.util.List;

public interface OrderService {
    Order save(OrderCommand command) throws InvalidAmountProductException;
    List<Order> getAllOrder(long idCustomer);
}
