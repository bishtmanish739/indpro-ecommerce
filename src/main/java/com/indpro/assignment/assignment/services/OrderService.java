package com.indpro.assignment.assignment.services;

import com.indpro.assignment.assignment.dtos.OrderRequestDTO;
import com.indpro.assignment.assignment.entity.Order;
import com.indpro.assignment.assignment.entity.OrderItem;
import com.indpro.assignment.assignment.entity.User;
import com.indpro.assignment.assignment.repository.OrderRepository;
import com.indpro.assignment.assignment.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ProductService productService;

    public Order createOrder(OrderRequestDTO orderRequest) throws BadRequestException {
      Long userId=  orderRequest.getUserId();
      //TODO: validate product available in stocks or not

//        List<Long> productIds = order.getProducts().stream()
//                .map(productDTO -> productDTO.getProductId())
//                .collect(Collectors.toList());
//        List<Product> products= productService.findAllById(productIds);

        Order order=new Order();
       User user=userRepository.findById(userId).orElseThrow(()->
           new BadRequestException("Not found")
       );
       //TODO: create order item
        List<OrderItem> orderItems=new ArrayList<>();
       //TODO: calculate price
       BigDecimal price= BigDecimal.valueOf(1111.0);
        order.setUser(user);
        order.setTotalPrice(price);
        order.setOrderItems(orderItems);
        order.setStatus("Under Process");

       return orderRepository.save(order);

    }

    public List<Order> getAllOrdersForUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order getOrderById(Long id)  throws BadRequestException{
        return orderRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Order not found"));
    }
}
