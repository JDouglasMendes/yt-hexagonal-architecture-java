package com.ms.order.application.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderCommand {
    private long idCustomer;

    public long getIdCustomer() {
        return idCustomer;
    }

    private ArrayList<OrderProductCommand> productCommands;

    public OrderCommand(long idCustomer) {
        this.idCustomer = idCustomer;
        this.productCommands = new ArrayList<>();
    }

    public void AddProduct(long idProduct, int amount){
        this.productCommands.add(new OrderProductCommand(idProduct, amount));
    }

    public List<Long> getProductsId(){
        return this.productCommands.stream().map(x -> x.getIdProduct()).collect(Collectors.toList());
    }

    public int getAmountByProduct(Long idProduct) {
        Optional<OrderProductCommand> product = productCommands.stream().filter(x -> x.getIdProduct() == idProduct).findFirst();
        if(!product.isPresent()) return 0;
        return product.get().getAmount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCommand command = (OrderCommand) o;
        return idCustomer == command.idCustomer && Objects.equals(productCommands, command.productCommands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCustomer, productCommands);
    }
}
