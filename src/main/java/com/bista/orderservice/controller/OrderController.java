package com.bista.orderservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/order")
public class OrderController {

	
	@GetMapping
	public ResponseEntity<String> greeting(){
		return new ResponseEntity<String>("Order service is up and running at port 8085", HttpStatus.OK);
	}
}
