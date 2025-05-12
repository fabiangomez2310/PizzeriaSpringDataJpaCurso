package com.fabiangomez.persistence.repository;

import com.fabiangomez.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author FABIANG
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,String >{
    
}