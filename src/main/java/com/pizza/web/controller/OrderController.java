package com.pizza.web.controller;

import com.pizza.persistence.entity.OrderEntity;
import com.pizza.persistence.projection.OrderSummary;
import com.pizza.service.OrderService;
import com.pizza.service.dto.RamdonOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders(){
        return ResponseEntity.ok(this.orderService.getAllOrders());
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders(){
        List<OrderEntity> orders = this.orderService.getTodayOrders();
        if(orders.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);

    }

    @GetMapping("/out")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders(){
        List<OrderEntity> orders = this.orderService.getOutsideOrders();
        if(orders.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String customerId){
        List<OrderEntity> orders = this.orderService.getCustomerOrders(customerId);
        if(orders.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/summary/{orderId}")
    public ResponseEntity<OrderSummary> getOrderSummary(@PathVariable Integer orderId) {
        OrderSummary orderInfo = this.orderService.getOrderSummary(orderId);
        if (orderInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderInfo);
    }

    @PostMapping("/ramdon")
    public ResponseEntity<Boolean> ramdonOrder(@RequestBody RamdonOrderDto ramdonOrderDto) {
        boolean ordertaken = this.orderService.saveRandomOrder(ramdonOrderDto);
        if (ordertaken) {
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.internalServerError().build();
        }
    }
}
