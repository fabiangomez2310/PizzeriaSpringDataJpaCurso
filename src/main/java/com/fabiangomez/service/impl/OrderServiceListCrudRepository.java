package com.fabiangomez.service.impl;

import com.fabiangomez.persistence.entity.OrderEntity;
import com.fabiangomez.persistence.repository.OrderRepository;
import com.fabiangomez.service.OrderService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FABIANG
 */
@Service
public class OrderServiceListCrudRepository implements OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderServiceListCrudRepository(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OrderEntity> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<OrderEntity> getById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public OrderEntity save(OrderEntity order) {
        return this.repository.save(order);
    }

    @Override
    public Optional<OrderEntity> update(OrderEntity order, Integer id) {
        if (this.repository.existsById(id)) {
            order.setIdOrder(id);
            return Optional.of(this.repository.save(order));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(Integer id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
