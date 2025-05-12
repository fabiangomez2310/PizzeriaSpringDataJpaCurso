package com.fabiangomez.web.controller;

import com.fabiangomez.persistence.entity.CustomerEntity;
import com.fabiangomez.service.CustomerService;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FABIANG
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getAll(){
        return ResponseEntity.ok(this.customerService.getAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> get(@PathVariable String id){
        return this.customerService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<CustomerEntity> save(@RequestBody CustomerEntity newCustomer){
        CustomerEntity savedCustomer = this.customerService.save(newCustomer);
        
        URI location = URI.create(String.format("/api/customers/%s", savedCustomer.getIdCustomer()));
        
        return ResponseEntity.created(location).body(savedCustomer);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CustomerEntity> update(@RequestBody CustomerEntity customer, @PathVariable String id){
        return this.customerService.update(customer, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        boolean deleted = this.customerService.delete(id);
        if(deleted) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
}
