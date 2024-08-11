package com.myweb.controller;

import com.myweb.dto.ApiResponse;
import com.myweb.dto.OrderDTO;
import com.myweb.entity.Book;
import com.myweb.entity.Order;
import com.myweb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    public OrderService orderService;


    @GetMapping("")
    ApiResponse<List<OrderDTO>> getOrders(
            @RequestParam(required = false) String minTotalAmount,
            @RequestParam(required = false) String maxTotalAmount,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String order ){

        if(minTotalAmount != null && maxTotalAmount != null && sortBy != null && order != null )
        {
            return  ApiResponse.<List<OrderDTO>>builder()
                    .success(true)
                    .result(orderService.getOrdersByCriteria(minTotalAmount, maxTotalAmount, sortBy, order))
                    .build();
        }
        else if(minTotalAmount != null && maxTotalAmount != null)
        {
            return  ApiResponse.<List<OrderDTO>>builder()
                    .success(true)
                    .result(orderService.getOrdersByTotalAmountRange(minTotalAmount, maxTotalAmount))
                    .build();
        }
        else if (sortBy != null && order != null)
        {
            return  ApiResponse.<List<OrderDTO>>builder()
                    .success(true)
                    .result(orderService.getOrdersSortedBy(sortBy, order))
                    .build();
        }
        else{
            return  ApiResponse.<List<OrderDTO>>builder()
                    .success(true)
                    .result(orderService.getAllOrder())
                    .build();
        }



    }

    @GetMapping("/{orderId}")
    ApiResponse<OrderDTO> getOrderbyId(@PathVariable String orderId){
        return  ApiResponse.<OrderDTO>builder()
                .success(true)
                .result(orderService.getOrderById(orderId))
                .build();
    }

    @PostMapping("/new-order")
    ApiResponse<Order> createOrder(@RequestBody OrderDTO orderDTO){
        return  ApiResponse.<Order>builder()
                .success(true)
                .result(orderService.createOrder(orderDTO))
                .build();
    }

    @PutMapping("/update/{orderId}")
    ApiResponse<Order> updateOrder(@PathVariable String orderId, @RequestBody OrderDTO orderDTO){
        return ApiResponse.<Order>builder()
                .success(true)
                .result(orderService.updateOrder(orderId, orderDTO))
                .build();
    }

    @DeleteMapping("/{orderId}")
    ApiResponse<Order> deleteOrder(@PathVariable String orderId){
        orderService.deleteOrder(orderId);
        return ApiResponse.<Order>builder()
                .success(true)
                .message("Delete order successfully")
                .build();
    }
}
