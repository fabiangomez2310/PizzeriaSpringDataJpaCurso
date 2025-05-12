package com.fabiangomez.persistence.repository;

import com.fabiangomez.persistence.entity.OrderItemEntity;
import com.fabiangomez.persistence.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author FABIANG
 */

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemId>{
    
}