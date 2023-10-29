package com.julian.restfulapi.entity.dto;

import com.julian.restfulapi.entity.Customer;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class StoreDTO {

    private Long storeId;
    private String name;
    private String floor;

}
