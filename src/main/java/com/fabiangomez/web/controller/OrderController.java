package com.fabiangomez.web.controller;

import com.fabiangomez.persistence.entity.OrderEntity;
import com.fabiangomez.service.OrderService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll() {
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> get(@PathVariable Integer id) {
        return this.orderService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/{id}")
    public ResponseEntity<OrderEntity> save(@RequestBody OrderEntity newOrder){
        OrderEntity savedOrder = this.orderService.save(newOrder);
        URI location = URI.create(String.format("/api/orders/%s", savedOrder.getIdOrder()));
        return ResponseEntity.created(location).body(savedOrder);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<OrderEntity> update(@RequestBody OrderEntity order, @PathVariable Integer id){
        return this.orderService.update(order, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        boolean deleted = this.orderService.delete(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    

}



















