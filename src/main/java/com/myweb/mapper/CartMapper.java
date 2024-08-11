package com.myweb.mapper;

import com.myweb.dto.CartDTO;
import com.myweb.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart toCart(CartDTO cartDTO);
    CartDTO toCartDTO(Cart cart);

    @Mapping(target = "cartId", ignore = true)
    void updateCart(CartDTO cartDTO, @MappingTarget Cart cart);
}

