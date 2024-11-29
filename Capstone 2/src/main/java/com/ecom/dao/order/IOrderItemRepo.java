package com.ecom.dao.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.order.OrderItem;
 
public interface IOrderItemRepo extends JpaRepository<OrderItem, Integer>{
 
}