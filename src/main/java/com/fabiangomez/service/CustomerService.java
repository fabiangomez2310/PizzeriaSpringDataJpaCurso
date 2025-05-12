package com.fabiangomez.service;

import com.fabiangomez.persistence.entity.CustomerEntity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author FABIANG
 */
public interface CustomerService {

    List<CustomerEntity> getAll();

    Optional<CustomerEntity> getById(String id);

    CustomerEntity save(CustomerEntity customer);

    Optional<CustomerEntity> update(CustomerEntity customer, String id);

    boolean delete(String id);

}
