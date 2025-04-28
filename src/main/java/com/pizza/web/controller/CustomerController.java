package com.pizza.web.controller;

import com.pizza.persistence.entity.CustomerEntity;
import com.pizza.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> findByPhone(@PathVariable String phone){
        CustomerEntity customerEnBD = this.customerService.findByPhone(phone);
        if(customerEnBD == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerEnBD);
    }
}
