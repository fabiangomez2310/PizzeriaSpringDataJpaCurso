package com.fabiangomez.service;

import com.fabiangomez.persistence.entity.OrderEntity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author FABIANG
 */
public interface OrderService {
    List<OrderEntity> getAll();
    Optional<OrderEntity> getById(Integer id);
    OrderEntity save(OrderEntity order);
    Optional<OrderEntity> update(OrderEntity order, Integer id);
    boolean delete(Integer id);
}
