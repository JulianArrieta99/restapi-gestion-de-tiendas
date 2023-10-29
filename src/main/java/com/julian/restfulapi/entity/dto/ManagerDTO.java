package com.julian.restfulapi.entity.dto;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Data
public class ManagerDTO {
    @NonNull
    private String managerName;
    @NonNull
    private String managerLastName;
    @NonNull
    private Long storeId;
}
