package com.bista.orderservice.service;

import com.bista.orderservice.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Order createOrder(Order order);
    List<Order> getAllOrders();
    List<Order> getUserOrders(String email);
    Order getOrderById(Long orderId);
}
