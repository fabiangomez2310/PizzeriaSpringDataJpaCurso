package com.fabiangomez.persistence.repository;

import com.fabiangomez.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author FABIANG
 */
@Repository
public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer>{
    
}