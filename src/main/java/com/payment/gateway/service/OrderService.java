package com.payment.gateway.service;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.payment.gateway.dao.OrderRepo;
import com.payment.gateway.dto.OrderDto;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderRepo;
	
	@Value("${razorpay.key.id}")
	private String razorPayKey;
	
	@Value("${razorpay.secret.key}")
	private String razorPaySecretKey;
	
	private RazorpayClient razorpayClient;
	
	public Boolean createOrder(OrderDto orderDto) {
		try {
			//create obj for our Order entity class
			com.payment.gateway.model.Order entityOrder = new com.payment.gateway.model.Order();
			
			JSONObject reqObject = new JSONObject();
			reqObject.put("amount", orderDto.getAmount()*100); // for razor pay amount always to be in paisa
			reqObject.put("currency", "INR");
			reqObject.put("receipt",orderDto.getEmail());
			
			this.razorpayClient = new RazorpayClient(razorPayKey, razorPaySecretKey);
			
			//create order in razorpay 
			Order razorPayOrder =  razorpayClient.orders.create(reqObject);
			entityOrder.setRazorPayOrderId(razorPayOrder.get("id"));
			entityOrder.setOrderStatus(razorPayOrder.get("status"));
			
			//copying data from dto to entity
			BeanUtils.copyProperties(orderDto, entityOrder);
			
			orderRepo.save(entityOrder);
			
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
}
