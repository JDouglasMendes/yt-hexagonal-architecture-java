package com.ms.order.adapters.persistence;

import com.ms.order.adapters.persistence.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepositoryJPA extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByIdCustomer(Long idCustomer);
}
