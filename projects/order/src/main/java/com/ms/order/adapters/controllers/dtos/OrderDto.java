package com.ms.order.adapters.controllers.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private long idCustomer;
    List<OrderProductDto> products;
}
