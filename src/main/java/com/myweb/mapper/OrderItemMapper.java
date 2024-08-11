package com.myweb.mapper;

import com.myweb.dto.OrderItemDTO;
import com.myweb.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mappings({
            @Mapping(source = "orderId", target = "order.orderId")
    })
    OrderItem toOrderItem (OrderItemDTO orderItemDTO);

    @Mappings({

            @Mapping(source = "order.orderId", target = "orderId")
    })
    OrderItemDTO toOrderItemDTO(OrderItem orderItem);
}
