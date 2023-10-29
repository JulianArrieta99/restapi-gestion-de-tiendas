package com.julian.restfulapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
@Table (
        name = "tbl_customers",
        uniqueConstraints = @UniqueConstraint(
                name = "email_unique",
                columnNames = "email_address"
        )

)
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "customer_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long customerId;

    @NotNull(message = "no debe ser nulo")
    @NotBlank(message = "no puede estar vacio")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull(message = "no debe ser nulo")
    @NotBlank(message = "no puede estar vacio")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull(message = "no debe ser nulo")
    @NotBlank(message = "no puede estar vacio")
    @Email(message = "Debe ser una dirección de correo electrónico válida")
    @Column(name = "email_address", nullable = false)
    private String email;

    @NotNull(message = "no debe ser nulo")
    @NotBlank(message = "no puede estar vacio")
    @Column(name = "password", nullable = false)
    private String password;

}
