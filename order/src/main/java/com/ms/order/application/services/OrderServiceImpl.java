package com.ms.order.application.services;

import com.ms.order.application.commands.OrderCommand;
import com.ms.order.application.domain.Customer;
import com.ms.order.application.domain.Order;
import com.ms.order.application.domain.Product;
import com.ms.order.application.exceptions.CustomerNotFoundException;
import com.ms.order.application.exceptions.InvalidAmountProductException;
import com.ms.order.application.exceptions.ProductNotFoundException;
import com.ms.order.application.ports.CustomerService;
import com.ms.order.application.ports.OrderRepository;
import com.ms.order.application.ports.OrderService;
import com.ms.order.application.ports.ProductService;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            CustomerService customerService,
                            ProductService productService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    @Override
    public Order save(OrderCommand command) throws InvalidAmountProductException {
        Optional<Customer> customer = Optional.ofNullable(customerService.getCustomer(command.getIdCustomer())
                .orElseThrow(() -> new CustomerNotFoundException(command.getIdCustomer())));

        Optional<List<Product>> products = Optional.ofNullable(productService.getProducts(command.getProductsId())
                .orElseThrow(ProductNotFoundException::new));

        Order order = new Order(customer.get());

        for (Product product : products.get()) order.AddProducts(product);

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrder(long idCustomer) {
        return orderRepository.getAllOrder(idCustomer);
    }
}
