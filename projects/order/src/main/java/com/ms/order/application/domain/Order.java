package com.ms.order.application.domain;

import com.ms.order.application.exceptions.InvalidAmountProductException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    private Customer customer;

    private ArrayList<Product> products;

    public double getTotal() {
        return total;
    }

    private double total;
    public Order(Customer customer) {
        this.customer = customer;
        this.products = new ArrayList<>();
    }

    public void AddProducts(Product product) throws InvalidAmountProductException {
        if (product.getAmount() <= 0)
            throw new InvalidAmountProductException();
        products.add(product);
        total += product.getValue() * product.getAmount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.total, total) == 0 && Objects.equals(customer, order.customer) && Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, products, total);
    }
}
