package com.ms.order.application.ports;

import com.ms.order.application.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<List<Product>> getProducts(List<Long> idProducts);
}
