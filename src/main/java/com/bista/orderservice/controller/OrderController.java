package com.bista.orderservice.controller;

import com.bista.orderservice.entity.Order;
import com.bista.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/order")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/greeting")
	public ResponseEntity<String> greeting(){
		return new ResponseEntity<String>("Order service is up and running at port 8085", HttpStatus.OK);
	}


	@PostMapping
	public  ResponseEntity<String> createOrder(@RequestBody @Valid Order order){
		order.setEmail(getUserEmail());
		Order savedOrder = orderService.createOrder(order);
		if(savedOrder != null){
			return  ResponseEntity.status(HttpStatus.OK).body("Order created successfully");
		}
		return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Order creation failed");
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable Long id){
		Order order = orderService.getOrderById(id);
		if(order != null){
			return ResponseEntity.status(HttpStatus.OK).body(order);
		}
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping
	public ResponseEntity<List<Order>> getAllOrders(){
		List<Order> orders = orderService.getAllOrders();
		if(orders != null){
			return ResponseEntity.status(HttpStatus.OK).body(orders);
		}
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
	}

	@GetMapping("/user")
	public ResponseEntity<List<Order>> getAllOrdersByUser(){
		String email = getUserEmail();
		List<Order> orders = orderService.getUserOrders(email);
		return  ResponseEntity.status(HttpStatus.OK).body(orders);
	}

	public String getUserEmail(){
		Authentication  auth = SecurityContextHolder.getContext().getAuthentication();
		Jwt jwt = (Jwt) auth.getPrincipal();
		return jwt.getClaims().get("email").toString();
	}

}
