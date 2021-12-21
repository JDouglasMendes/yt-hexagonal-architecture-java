package com.ms.order.application.domain;

import java.util.Objects;

public class Product {
    private long id;
    private String name;
    private double value;
    private int amount;

    public int getAmount() {
        return amount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public Product(long id, String name, double value, int amount) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(product.value, value) == 0 && amount == product.amount && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value, amount);
    }
}
