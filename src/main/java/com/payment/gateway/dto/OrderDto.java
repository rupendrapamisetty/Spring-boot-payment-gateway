package com.payment.gateway.dto;

import lombok.Data;

@Data
public class OrderDto {
	
	private String name;
	
	private String email;
	
	private String phno;
	
	private String course;
	
	private Double amount;
}
