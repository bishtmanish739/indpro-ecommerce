package com.indpro.assignment.assignment.repository;

import com.indpro.assignment.assignment.entity.Order;
import com.indpro.assignment.assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    List<Order> findByUser(User user);
}
