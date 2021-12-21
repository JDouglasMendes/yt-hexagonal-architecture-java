package com.ms.order.application.commands;

import java.util.Objects;

public class OrderProductCommand {
    private long idProduct;
    private int amount;

    public long getIdProduct() {
        return idProduct;
    }

    public int getAmount() {
        return amount;
    }

    public OrderProductCommand(long idProduct, int amount) {
        this.idProduct = idProduct;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductCommand that = (OrderProductCommand) o;
        return idProduct == that.idProduct && amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, amount);
    }
}
