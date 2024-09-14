package com.indpro.assignment.assignment.controller;

import com.indpro.assignment.assignment.dtos.OrderRequestDTO;
import com.indpro.assignment.assignment.entity.Order;
import com.indpro.assignment.assignment.repository.UserRepository;
import com.indpro.assignment.assignment.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO order) throws Exception {

        Order order1= orderService.createOrder(order);
       return  ResponseEntity.ok(order1);

    }

    @GetMapping
    public List<Order> getOrders(Authentication authentication) throws  Exception{

        return orderService.getAllOrdersForUser(1l);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) throws Exception{
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
}
