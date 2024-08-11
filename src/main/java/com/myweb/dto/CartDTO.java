package com.myweb.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartDTO {
    Long cartId;
    BigDecimal totalAmount;
    List<CartItemDTO> cartItemDTOList;
    Long userId;
}
