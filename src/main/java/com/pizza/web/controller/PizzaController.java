package com.pizza.web.controller;

import com.pizza.persistence.entity.PizzaEntity;
import com.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll(){
        return ResponseEntity.ok(this.pizzaService.getAll());
    }

    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAvailable(){
        return ResponseEntity.ok(this.pizzaService.getAvailable());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getPizzaByName(@PathVariable String name){
        PizzaEntity pizzaFound = this.pizzaService.findByName(name);
        if(pizzaFound == null){ return ResponseEntity.notFound().build(); }
        return ResponseEntity.ok(pizzaFound);
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<?> getWith(@PathVariable String ingredient){
        List<PizzaEntity> pizzaFound = this.pizzaService.getWith(ingredient);
        if(pizzaFound.isEmpty()){ return ResponseEntity.notFound().build(); }
        return ResponseEntity.ok(pizzaFound);
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<?> getWithout(@PathVariable String ingredient){
        List<PizzaEntity> pizzaFound = this.pizzaService.getWithout(ingredient);
        if(pizzaFound.isEmpty()){ return ResponseEntity.notFound().build(); }
        return ResponseEntity.ok(pizzaFound);
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<?> getById(@PathVariable Integer idPizza){
        if(!this.pizzaService.exists(idPizza)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La pizza no fue encontrada!");
        }
        return ResponseEntity.ok(this.pizzaService.getById(idPizza));
    }

    @PostMapping
    public ResponseEntity<?> addPizza(@RequestBody PizzaEntity pizza){
        try{

            if (pizza.getIdPizza() == 0 || !this.pizzaService.exists(pizza.getIdPizza())) {
                pizza.setIdPizza(null);
                return ResponseEntity.ok(this.pizzaService.save(pizza));
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La Pizza ya Existe!");
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<?> updatePizza(@RequestBody PizzaEntity pizza){
        try{
            if (pizza.getIdPizza() != 0 || this.pizzaService.exists(pizza.getIdPizza())) {
                return ResponseEntity.ok(this.pizzaService.save(pizza));
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La Pizza NO Existe!");
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> deletePizza(@PathVariable Integer idPizza){
        if(this.pizzaService.exists(idPizza)){
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapestPizza(@PathVariable Double price){
        return ResponseEntity.ok(this.pizzaService.getCheapest(price));
    }
}
