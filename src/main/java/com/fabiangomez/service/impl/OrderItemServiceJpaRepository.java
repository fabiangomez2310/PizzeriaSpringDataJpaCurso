package com.fabiangomez.service.impl;

import com.fabiangomez.persistence.entity.OrderItemEntity;
import com.fabiangomez.persistence.entity.OrderItemId;
import com.fabiangomez.persistence.repository.OrderItemRepository;
import com.fabiangomez.service.OrderItemService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author FABIANG
 */
@Service
public class OrderItemServiceJpaRepository implements OrderItemService {

    private final OrderItemRepository repository;

    public OrderItemServiceJpaRepository(OrderItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OrderItemEntity> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<OrderItemEntity> getById(OrderItemId id) {
        return this.repository.findById(id);
    }

    @Override
    public OrderItemEntity save(OrderItemEntity orderItem) {
        return this.repository.save(orderItem);
    }

    @Override
    public Optional<OrderItemEntity> update(OrderItemEntity orderItem, OrderItemId id) {
        if (this.repository.existsById(id)) {
            orderItem.setIdOrder(id.getIdOrder());
            orderItem.setIdItem(id.getIdItem());

            return Optional.of(this.repository.save(orderItem));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(OrderItemId id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
