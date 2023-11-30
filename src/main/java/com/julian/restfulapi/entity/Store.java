package com.julian.restfulapi.entity;
import jakarta.persistence.*;
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
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long storeId;
        private String name;
        private String floor;

        @OneToOne(
                cascade = CascadeType.PERSIST,
                fetch = FetchType.EAGER

        )
        @JoinColumn(name = "manager_id", referencedColumnName = "managerId")
        private Manager manager;

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

