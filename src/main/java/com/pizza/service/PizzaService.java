package com.pizza.service;

import com.pizza.persistence.entity.PizzaEntity;
import com.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> getAll(){
        return pizzaRepository.findAll();
    }

    public PizzaEntity getById(Integer id){
        return pizzaRepository.findById(id).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }

    public boolean exists(Integer id){
        return this.pizzaRepository.existsById(id);
    }

    public void delete(Integer id){
        this.pizzaRepository.deleteById(id);
    }

}
