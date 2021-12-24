package com.ms.order.adapters.persistence.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "OrderItem")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class OrderItemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Column
    @NonNull
    private long idProduct;
    @Column(length = 100)
    @NonNull
    private String descriptionProduct;
    @Column
    @NonNull
    private double value;
    @Column
    @NonNull
    private int amount;
}
