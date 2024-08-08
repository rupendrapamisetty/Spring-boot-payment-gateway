package com.payment.gateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payment.gateway.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

}
