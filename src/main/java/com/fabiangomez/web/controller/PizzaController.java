/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fabiangomez.web.controller;

import com.fabiangomez.persistence.entity.PizzaEntity;
import com.fabiangomez.service.PizzaServiceJdbcTemplate;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FABIANG
 */
@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaServiceJdbcTemplate pizzaService;

    @Autowired
    public PizzaController(PizzaServiceJdbcTemplate pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll() {
        return ResponseEntity.ok(this.pizzaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> get(@PathVariable Integer id) {
        return this.pizzaService.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity newPizza) {
        PizzaEntity savedPizza = this.pizzaService.save(newPizza);
        URI location = URI.create(String.format("api/pizzas/%d", savedPizza.getIdPizza()));
        return ResponseEntity.created(location).body(savedPizza);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza, @PathVariable Integer id) {
        return this.pizzaService.update(pizza, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = this.pizzaService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
