package com.julian.restfulapi.entity.mapper;

import com.julian.restfulapi.entity.Manager;
import com.julian.restfulapi.entity.Order;
import com.julian.restfulapi.entity.dto.ManagerDTO;
import com.julian.restfulapi.entity.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ManagerMapper {

    ManagerMapper INSTANCE = Mappers.getMapper(ManagerMapper.class);


    Manager managerDTOToManager(ManagerDTO managerDTO);
}
