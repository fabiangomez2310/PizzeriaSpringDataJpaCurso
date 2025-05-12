package com.fabiangomez.persistence.repository;

import com.fabiangomez.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

/**
 *
 * @author FABIANG
 */
public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer>{
    
}