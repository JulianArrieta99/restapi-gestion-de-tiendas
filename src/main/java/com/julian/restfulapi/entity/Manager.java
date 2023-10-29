package com.julian.restfulapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_managers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Manager {

    @Id
    @SequenceGenerator(
            name = "manager_sequence",
            sequenceName = "manager_sequence",
            allocationSize = 1
    )
    @GeneratedValue(generator = "manager_sequence",
    strategy = GenerationType.SEQUENCE
    )

    private Long managerId;

    private String managerName;

    private String managerLastName;

    @OneToOne
    @JoinColumn(
            name = "store_id", referencedColumnName = "storeId"
    )
    private Store store;
}
