package com.fabiangomez.service;

import com.fabiangomez.persistence.entity.OrderItemEntity;
import com.fabiangomez.persistence.entity.OrderItemId;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author FABIANG
 */
public interface OrderItemService {
    List<OrderItemEntity> getAll();
    Optional<OrderItemEntity> getById(OrderItemId id);
    OrderItemEntity save(OrderItemEntity orderItem);
    Optional<OrderItemEntity> update (OrderItemEntity orderItem, OrderItemId id);
    boolean delete(OrderItemId id);
    
}
