package com.myweb.dto;

import com.myweb.entity.OrderItem;
import com.myweb.enums.OrderStatus;
import com.myweb.enums.PaymentMethod;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTO {
    String orderId;
    LocalDateTime orderTime;
    BigDecimal totalAmount;
    OrderStatus orderStatus;
    PaymentMethod paymentMethod;
    List<OrderItem> orderItems;
    Long userId;
}
