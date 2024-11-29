package com.ecom.dao.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.order.Payment;
 
public interface IPaymentRepo extends JpaRepository<Payment, Integer>{
 
}