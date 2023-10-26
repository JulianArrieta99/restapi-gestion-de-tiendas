package com.julian.restfulapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @SequenceGenerator(name = "order_sequence")
    @GeneratedValue(generator = "order_sequence", strategy = GenerationType.SEQUENCE)
    private Long orderId;
    private String description;
    private Double price;

    @ManyToOne(
            cascade = CascadeType.ALL

    )
    @JoinColumn(
            name = "store_id",
            referencedColumnName = "storeId"
    )
    private Store store;
}
