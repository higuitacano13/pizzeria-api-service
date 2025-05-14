package com.pizza.web.controller;

import com.pizza.persistence.entity.CustomerEntity;
import com.pizza.persistence.entity.OrderEntity;
import com.pizza.service.CustomerService;
import com.pizza.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> findByPhone(@PathVariable String phone){
        CustomerEntity customerEnBD = this.customerService.findByPhone(phone);
        if(customerEnBD == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerEnBD);
    }

    @GetMapping("{customerId}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String customerId){
        List<OrderEntity> orders = this.orderService.getCustomerOrders(customerId);
        if(orders.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }
}
