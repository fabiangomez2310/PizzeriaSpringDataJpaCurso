package com.fabiangomez.service.impl;

import com.fabiangomez.persistence.entity.CustomerEntity;
import com.fabiangomez.persistence.repository.CustomerRepository;
import com.fabiangomez.service.CustomerService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author FABIANG
 */
@Service
public class CustomerServiceJpaRepository implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceJpaRepository(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CustomerEntity> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<CustomerEntity> getById(String id) {
        return this.repository.findById(id);
    }

    @Override
    public CustomerEntity save(CustomerEntity customer) {
        return this.repository.save(customer);
    }

    @Override
    public Optional<CustomerEntity> update(CustomerEntity customer, String id) {
        if (this.repository.existsById(id)) {
            customer.setIdCustomer(id);
            return Optional.of(this.repository.save(customer));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(String id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
