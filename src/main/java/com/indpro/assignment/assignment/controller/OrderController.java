package com.indpro.assignment.assignment.controller;

import com.indpro.assignment.assignment.dtos.OrderDTO;
import com.indpro.assignment.assignment.dtos.OrderItemRequest;
import com.indpro.assignment.assignment.entity.Order;
import com.indpro.assignment.assignment.entity.User;
import com.indpro.assignment.assignment.services.OrderService;
import com.indpro.assignment.assignment.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody List<OrderItemRequest> items, Principal principal) throws Exception {
        User user = userService.getUserByUsername(principal.getName());
        log.debug("user details {}",user);
        Order order = orderService.createOrder(user, items);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        List<OrderDTO> orders = orderService.getOrders(user);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return orderService.getOrder(id, user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
