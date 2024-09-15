package com.indpro.assignment.assignment.services;

import com.indpro.assignment.assignment.dtos.OrderDTO;
import com.indpro.assignment.assignment.dtos.OrderItemRequest;
import com.indpro.assignment.assignment.entity.Order;
import com.indpro.assignment.assignment.entity.OrderItem;
import com.indpro.assignment.assignment.entity.Product;
import com.indpro.assignment.assignment.entity.User;
import com.indpro.assignment.assignment.mapper.OrderMapper;
import com.indpro.assignment.assignment.repository.OrderItemRepository;
import com.indpro.assignment.assignment.repository.OrderRepository;
import com.indpro.assignment.assignment.repository.ProductRepository;
import com.indpro.assignment.assignment.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderMapper orderMapper;

    public Order createOrder(User user, List<OrderItemRequest> items) throws Exception {
        Order order = new Order();
        order.setUser(user);
        order.setStatus("Pending");
        //TOOD: Calculate price
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest item : items) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new BadRequestException("Product not found"));

            if (product.getStock() < item.getQuantity()) {
                throw new IllegalStateException("Not enough stock for product: " + product.getName());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));

            totalPrice = totalPrice.add(orderItem.getPrice());
            orderItems.add(orderItem);

            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
        }

        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);
        orderRepository.save(order);

        return order;
    }

    public List<OrderDTO> getOrders(User user) {
        List<Order> orders= orderRepository.findByUser(user);
        return orderMapper.toDtoList(orders);
    }

    public Optional<Order> getOrder(Long orderId, User user) {
        return orderRepository.findById(orderId)
                .filter(order -> order.getUser().equals(user));
    }
}


