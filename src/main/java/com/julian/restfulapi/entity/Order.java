package com.julian.restfulapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Order {
    @Id
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence")
    @GeneratedValue(generator = "order_sequence", strategy = GenerationType.SEQUENCE)
    private Long orderId;
    private String description;
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "storeId")
    @JsonIgnore
    private Store store;
}
