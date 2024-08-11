package com.myweb.mapper;

import com.myweb.dto.OrderDTO;
import com.myweb.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toOrder(OrderDTO orderDto);

    @Mapping(source = "user.userId", target = "userId")
    OrderDTO toOrderDto(Order order);

    void updateOrder(OrderDTO orderDTO, @MappingTarget Order order);
}
