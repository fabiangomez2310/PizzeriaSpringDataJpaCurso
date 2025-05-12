package com.fabiangomez.web.controller;

import com.fabiangomez.persistence.entity.OrderItemEntity;
import com.fabiangomez.persistence.entity.OrderItemId;
import com.fabiangomez.service.OrderItemService;
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
@RequestMapping("/api/orderitems")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public ResponseEntity<List<OrderItemEntity>> getAll() {
        return ResponseEntity.ok(this.orderItemService.getAll());
    }

    @GetMapping("/{idOrder}/{idItem}")
    public ResponseEntity<OrderItemEntity> get(@PathVariable Integer idOrder, @PathVariable Integer idItem) {

        OrderItemId id = new OrderItemId(idOrder, idItem);

        return this.orderItemService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderItemEntity> save(@RequestBody OrderItemEntity newOrderItem) {
        OrderItemEntity savedOrderItem = this.orderItemService.save(newOrderItem);

        URI location = URI.create(String.format(
                "/api/orderitems/%d/%d",
                savedOrderItem.getIdOrder(),
                savedOrderItem.getIdItem()
        ));

        return ResponseEntity.created(location).body(savedOrderItem);

    }

    @PutMapping("/{idOrder}/{idItem}")
    public ResponseEntity<OrderItemEntity> update(
            @PathVariable Integer idOrder,
            @PathVariable Integer idItem,
            @RequestBody OrderItemEntity orderItem) {

        OrderItemId id = new OrderItemId(idOrder, idItem);

        return this.orderItemService.update(orderItem, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{idOrder}/{idItem}")
    public ResponseEntity<OrderItemEntity> delete(
            @PathVariable Integer idOrder,
            @PathVariable Integer idItem){
        OrderItemId id = new OrderItemId(idOrder, idItem);
        
        if(this.orderItemService.delete(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
