package com.payment.gateway.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;

	private String name;
	
	private String email;
	
	private String phno;
	
	private String course;
	
	private Double amount;
	
	private String orderStatus;
	
	private String razorPayOrderId;
}
