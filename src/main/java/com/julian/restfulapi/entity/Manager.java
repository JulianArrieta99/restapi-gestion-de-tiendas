package com.julian.restfulapi.entity;

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
            name = "manager_sequence"
    )
    @GeneratedValue(generator = "manager_sequence",
    strategy = GenerationType.SEQUENCE
    )

    private Long managerId;

    private String firstName;

    private String lastName;

    @OneToOne(
            mappedBy = "manager",
            fetch = FetchType.EAGER
    )
    private Local local;
}
