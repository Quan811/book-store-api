package com.myweb.controller;

import com.myweb.dto.ApiResponse;
import com.myweb.dto.OrderItemDTO;
import com.myweb.entity.OrderItem;
import com.myweb.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;
    @GetMapping("")
    ApiResponse<List<OrderItemDTO>> getALlOrderItem(){
        return ApiResponse.<List<OrderItemDTO>>builder()
                .success(true)
                .result(orderItemService.getALlOrderItem())
                .build();

    }

    @GetMapping("/{orderItemId}")
    ApiResponse<OrderItemDTO> getOrderItemById(@PathVariable Long orderItemId){
        return ApiResponse.<OrderItemDTO>builder()
                .success(true)
                .result(orderItemService.getOrderItemById(orderItemId))
                .build();
    }

    @PostMapping("")
    ApiResponse<OrderItemDTO> createOrderItem(@RequestBody OrderItemDTO orderItemDTO){
        return ApiResponse.<OrderItemDTO>builder()
                .success(true)
                .result(orderItemService.createOrderItem(orderItemDTO))
                .build();

    }

    @PutMapping("/update/{orderItemId}")
    ApiResponse<OrderItemDTO> updateOrderItem(@PathVariable Long orderItemId ,@RequestBody OrderItemDTO orderItemDTO){
        return ApiResponse.<OrderItemDTO>builder()
                .success(true)
                .result(orderItemService.updateOrderItem(orderItemId,orderItemDTO))
                .build();
    }

    @DeleteMapping("/{orderItemId}")
    ApiResponse<OrderItem> deleteOrderItem(@PathVariable Long orderItemId){
        orderItemService.deleteOrderItem(orderItemId);
        return ApiResponse.<OrderItem>builder()
                .success(true)
                .message("Delete order item successfully")
                .build();
    }
}
