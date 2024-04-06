package com.demo.microservices.controllers;

import com.demo.microservices.models.Order;
import com.demo.microservices.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity
                .ok(orderRepository.findAll());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getById(@PathVariable String orderId) {
        return ResponseEntity.of(orderRepository.findById(orderId));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Order order) {
        try {
            Order inserted = orderRepository.insert(order);
            return ResponseEntity
                    .ok("Created Order :" + inserted.getOrderId());
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Order order) {
        Optional<Order> found = orderRepository.findById(order.getOrderId());
        if (found.isPresent()) {
            Order foundOrder = found.get();
            BeanUtils.copyProperties(order, foundOrder);
            try {
                orderRepository.save(foundOrder);
                return ResponseEntity.ok("Order updated successfully");
            } catch (Exception e) {
                return ResponseEntity
                        .badRequest()
                        .body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> update(@PathVariable String orderId) {
        try {
            orderRepository.deleteById(orderId);
            return ResponseEntity
                    .ok("Order has been deleted");
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

}
