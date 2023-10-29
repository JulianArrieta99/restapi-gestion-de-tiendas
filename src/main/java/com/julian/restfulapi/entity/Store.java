package com.julian.restfulapi.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @EqualsAndHashCode

    public class Store {
        @Id
        @SequenceGenerator(
                name = "store_sequence",
                sequenceName = "store_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                generator = "store_sequence",
                strategy = GenerationType.SEQUENCE
        )
        private Long storeId;
        private String name;
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


    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;


    }

