package com.julian.restfulapi.entity.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OrderDTO {
    @NonNull
    private String description;
    @NonNull
    private Double price;

    @NonNull
    private Long storeId;
}
