package com.myweb.service;

import com.myweb.dto.OrderDTO;
import com.myweb.dto.UserDTO;
import com.myweb.entity.Order;
import com.myweb.entity.User;
import com.myweb.enums.OrderStatus;
import com.myweb.mapper.OrderMapper;
import com.myweb.mapper.UserMapper;
import com.myweb.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public OrderMapper orderMapper;
    @Autowired
    public EntityManager entityManager;

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);


    public List<OrderDTO> getAllOrder(){
        List<Order> orderList = orderRepository.findAll();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for(Order order: orderList){
            orderDTOList.add(orderMapper.toOrderDto(order));
        }
        return orderDTOList;
    }

    public List<OrderDTO> getOrdersSortedBy(String sortBy, String order){
        Sort.Direction direction = order.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        List<Order> orders = orderRepository.findAll(Sort.by(direction, sortBy));
        List<OrderDTO> listResult = new ArrayList<>();
        for(Order o : orders){
            listResult.add(orderMapper.toOrderDto(o));
        }
        return listResult;
    }

    public List<OrderDTO> getOrdersByTotalAmountRange(String minTotalAmount, String maxTotalAmount){
        if(!orderRepository.getOrdersByTotalAmountRange(minTotalAmount, maxTotalAmount).isEmpty()){
            List<Order> orders = orderRepository.getOrdersByTotalAmountRange(minTotalAmount, maxTotalAmount);
            List<OrderDTO> listResult = new ArrayList<>();
            for(Order o : orders){
                listResult.add(orderMapper.toOrderDto(o));
            }
            return listResult;
        }
        else{
            throw new RuntimeException("Cannot find order with total amount between "+minTotalAmount+" and "+maxTotalAmount);
        }
    }


    public List<OrderDTO> getOrdersByCriteria(String minTotalAmount, String maxTotalAmount, String sortBy, String order) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        if (minTotalAmount != null && maxTotalAmount != null) {
            criteriaQuery.where(
                    criteriaBuilder.between(root.get("totalAmount"), minTotalAmount, maxTotalAmount)
            );
        }

        // Sắp xếp ORDER BY
        if (sortBy != null && order != null) {
            if (order.equalsIgnoreCase("asc")) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortBy)));
            } else if (order.equalsIgnoreCase("desc")) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sortBy)));
            }
        }

        List<Order> orders = entityManager.createQuery(criteriaQuery).getResultList();

        if(!orders.isEmpty()){
            List<OrderDTO> listResult = new ArrayList<>();
            for(Order o : orders){
                listResult.add(orderMapper.toOrderDto(o));
            }
            return listResult;
        }
        else{
            throw new RuntimeException("Cannot find matching orders");
        }




    }

    public OrderDTO getOrderById(String orderId){
        return orderMapper.toOrderDto(
                orderRepository.findById(orderId)
                        .orElseThrow(() -> new RuntimeException("Order not found")));
    }
    public Order createOrder(OrderDTO orderDto) {
        User user = userService.getUserById(orderDto.getUserId());
        Order order = orderMapper.toOrder(orderDto);
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PROCESSING);
        order.setOrderTime(LocalDateTime.now());

        return orderRepository.save(order);
    }


    public Order updateOrder(String orderId, OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        orderMapper.updateOrder(orderDTO, order);

        return orderRepository.save(order);
    }

    public void deleteOrder(String orderId){
        if(orderRepository.findById(orderId).isPresent())
        {
            orderRepository.deleteById(orderId);
        }
        else
        {
            throw new RuntimeException("Cannot find Order with id: " + orderId);
        }
    }
}
