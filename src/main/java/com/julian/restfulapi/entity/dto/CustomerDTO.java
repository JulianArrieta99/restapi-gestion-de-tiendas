package com.julian.restfulapi.entity.dto;

import com.julian.restfulapi.entity.Address;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @EqualsAndHashCode @ToString
public class CustomerDTO {

    private String email;
    private String firstName;
    private String lastName;
    private Address address;
}
