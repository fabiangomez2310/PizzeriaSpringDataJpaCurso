/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fabiangomez.persistence.repository;

import com.fabiangomez.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

/**
 *
 * @author FABIANG
 */
public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer>{
    
}
