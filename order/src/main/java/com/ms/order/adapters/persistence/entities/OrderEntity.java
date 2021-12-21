package com.ms.order.adapters.persistence.entities;

import com.ms.order.application.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Order")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column
    private long idCustomer;
    @Column
    private String customerFirstName;
    @Column
    private String customerLastName;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<OrderItemEntity> orderItemSet;
}
