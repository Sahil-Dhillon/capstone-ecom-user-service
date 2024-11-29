package com.ecom.dao.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.order.Order;
 
public interface IOrderRepo extends JpaRepository<Order, Integer>{
 
}