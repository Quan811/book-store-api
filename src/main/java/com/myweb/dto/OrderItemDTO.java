package com.myweb.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myweb.entity.Book;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemDTO {
    String orderItemId;
    String orderId;
    @JsonIgnoreProperties("reviews")
    Book book;
    Integer quantity;
    BigDecimal price;
}
