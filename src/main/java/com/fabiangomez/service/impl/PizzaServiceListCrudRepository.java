/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fabiangomez.service.impl;

import com.fabiangomez.persistence.entity.PizzaEntity;
import com.fabiangomez.persistence.repository.PizzaRepository;
import com.fabiangomez.service.PizzaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FABIANG
 */
@Service
public class PizzaServiceListCrudRepository implements PizzaService {

    private final PizzaRepository repository;

    @Autowired
    public PizzaServiceListCrudRepository(PizzaRepository repository) {
        this.repository = repository;

    }

    @Override
    public List<PizzaEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<PizzaEntity> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public PizzaEntity save(PizzaEntity pizza) {
        return repository.save(pizza);
    }

    @Override
    public Optional<PizzaEntity> update(PizzaEntity pizza, Integer id) {
        if (repository.existsById(id)) {
            pizza.setIdPizza(id);
            return Optional.of(repository.save(pizza));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }

}
