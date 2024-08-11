package com.myweb.service;

import com.myweb.dto.ApiResponse;
import com.myweb.dto.OrderItemDTO;
import com.myweb.entity.Order;
import com.myweb.entity.OrderItem;
import com.myweb.mapper.OrderItemMapper;
import com.myweb.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderItemMapper orderItemMapper;

    public List<OrderItemDTO> getALlOrderItem(){
        List<OrderItem> orderItems = orderItemRepository.findAll();
        List<OrderItemDTO> orderItemDTOs = new ArrayList<>();
        for(OrderItem orderItem : orderItems){
            orderItemDTOs.add(orderItemMapper.toOrderItemDTO(orderItem));
        }
        return orderItemDTOs;
    }

    public OrderItemDTO getOrderItemById(Long orderItemId){
        OrderItem orderItems = orderItemRepository.findById(orderItemId)
                .orElseThrow(()-> new RuntimeException("Order item not found"));
        return orderItemMapper.toOrderItemDTO(orderItems);
    }

    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO){
        OrderItem orderItem = orderItemMapper.toOrderItem((orderItemDTO));

        orderItemRepository.save(orderItem);
        return orderItemMapper.toOrderItemDTO(orderItem);
    }

    public OrderItemDTO updateOrderItem(Long orderItemId , OrderItemDTO orderItemDTO){
        if(orderItemRepository.findById(orderItemId).isPresent()){
            OrderItem orderItem = orderItemMapper.toOrderItem((orderItemDTO));
            orderItemRepository.save(orderItem);
            return orderItemMapper.toOrderItemDTO(orderItem);
        }
        else{
            throw new RuntimeException("Order item not found");
        }

    }

    public void deleteOrderItem(Long orderItemId){
        if(orderItemRepository.findById(orderItemId).isPresent()){
            orderItemRepository.deleteById(orderItemId);
        }
        else{
            throw new RuntimeException("Order item not found");
        }
    }
}
