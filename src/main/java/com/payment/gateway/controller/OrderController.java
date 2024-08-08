package com.payment.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.gateway.dto.OrderDto;
import com.payment.gateway.service.OrderService;

@RestController
@RequestMapping("/order-payment")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create-order")
	public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto){
		System.out.println("order dto"+orderDto);
		try {
			var result = orderService.createOrder(orderDto);
			return ResponseEntity.ok(result);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Backend error");
		}
	}

}
