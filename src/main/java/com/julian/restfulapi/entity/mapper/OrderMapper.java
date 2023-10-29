package com.julian.restfulapi.entity.mapper;

import com.julian.restfulapi.entity.Customer;
import com.julian.restfulapi.entity.Order;
import com.julian.restfulapi.entity.Store;
import com.julian.restfulapi.entity.dto.CustomerDTO;
import com.julian.restfulapi.entity.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


    Order orderDTOToOrder(OrderDTO orderDTO);

}
