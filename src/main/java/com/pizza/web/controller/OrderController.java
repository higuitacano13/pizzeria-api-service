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
        return ResponseEntity.ok(this.orderService.getOutsideOrders());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String customerId){
        return ResponseEntity.ok(this.orderService.getCustomerOrders(customerId));
    }

    @GetMapping("/summary/{orderId}")
    public ResponseEntity<OrderSummary> getOrderSummary(@PathVariable Integer orderId) {
        return ResponseEntity.ok(this.orderService.getOrderSummary(orderId));
    }

    @PostMapping("/ramdon")
    public ResponseEntity<Boolean> ramdonOrder(@RequestBody RamdonOrderDto ramdonOrderDto) {
        return ResponseEntity.ok(this.orderService.saveRandomOrder(ramdonOrderDto));
    }
}
