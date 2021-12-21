package com.ms.order.application.ports;

import com.ms.order.application.domain.Customer;

import java.util.Optional;

public interface CustomerService {
    Optional<Customer> getCustomer(long idCustomer);
}
