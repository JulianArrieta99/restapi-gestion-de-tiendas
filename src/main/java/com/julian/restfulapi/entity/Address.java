package com.julian.restfulapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
@AttributeOverrides({
        @AttributeOverride(
                name = "city",
                column = @Column(name = "city")
        ),
        @AttributeOverride(
                name = "mainStreet",
                column = @Column(name = "main_street")
        ),
        @AttributeOverride(
                name = "secondaryStreet",
                column = @Column(name = "secondary_street")
        )
})
public class Address {
    private String city;
    private String mainStreet;
    private String secondaryStreet;
}
