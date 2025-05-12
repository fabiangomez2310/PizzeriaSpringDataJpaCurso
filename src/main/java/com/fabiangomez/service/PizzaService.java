/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fabiangomez.service;

import com.fabiangomez.persistence.entity.PizzaEntity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author FABIANG
 */
public interface PizzaService {
    List<PizzaEntity> getAll();
    Optional<PizzaEntity> getById(Integer id);
    PizzaEntity save(PizzaEntity pizza);
    Optional<PizzaEntity> update(PizzaEntity pizza, Integer id);
    boolean delete(Integer id);
}
