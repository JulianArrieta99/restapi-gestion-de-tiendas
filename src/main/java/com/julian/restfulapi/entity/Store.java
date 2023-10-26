package com.julian.restfulapi.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
    @Table(name = "tbl_stores")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString(exclude = "manager")

    public class Store {
        @Id
        @SequenceGenerator(
                name = "local_sequence",
                sequenceName = "local_sequence"
        )
        @GeneratedValue(
                generator = "local_sequence",
                strategy = GenerationType.SEQUENCE
        )
        private Long storeId;
        @NotBlank(message = "Name no puede estar vacio")
        private String name;
        @NotBlank(message = "Floor no puede estar vacio")
        private String floor;

        @OneToOne(
                cascade = CascadeType.PERSIST,
                fetch = FetchType.EAGER,
                optional = true  // si el manager es obligatorio a la hora de crear un store o no

        )
        @JoinColumn(name = "manager_id", referencedColumnName = "managerId")
        private Manager manager;

        // en many to many el fetchType viene en lazy por defecto
        @ManyToMany(
                cascade = CascadeType.ALL,
                fetch = FetchType.EAGER
        )
        @JoinTable(
                name = "store_customer_map",
                joinColumns = @JoinColumn(
                        name = "store_id",
                        referencedColumnName = "storeId"
                ),
                inverseJoinColumns = @JoinColumn(
                        name = "customer_id",
                        referencedColumnName = "customerId"
                )
        )
        private List<Customer> customerList;




    }

