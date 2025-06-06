package com.pizza.service;

import com.pizza.persistence.entity.OrderEntity;
import com.pizza.persistence.projection.OrderSummary;
import com.pizza.persistence.repository.OrderRepository;
import com.pizza.service.dto.RamdonOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ONSITE = "S";

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAllOrders() {
        return this.orderRepository.findAll();
    }

    public List<OrderEntity> getTodayOrders() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.orderRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity> getOutsideOrders() {
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    @Secured("ROLE_ADMIN")
    public List<OrderEntity> getCustomerOrders(String customerId) {
        return this.orderRepository.findCustomerOrders(customerId);
    }

    public OrderSummary getOrderSummary(Integer orderId) {
        return this.orderRepository.findSummary(orderId);
    }

    @Transactional
    public boolean saveRandomOrder(RamdonOrderDto ramdonOrderDto) {
        return this.orderRepository.saveRandomOrder(ramdonOrderDto.getIdCustomer(), ramdonOrderDto.getMethod());
    }


}
