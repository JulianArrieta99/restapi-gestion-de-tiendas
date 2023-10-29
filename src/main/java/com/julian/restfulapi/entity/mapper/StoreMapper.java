package com.julian.restfulapi.entity.mapper;

import com.julian.restfulapi.entity.Store;
import com.julian.restfulapi.entity.dto.StoreDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StoreMapper {
    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    @Mapping(target = "storeId", source = "store.storeId")
    StoreDTO storeToStoreDTO(Store store);
}
