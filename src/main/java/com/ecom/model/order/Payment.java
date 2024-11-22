package com.ecom.model.order;

import java.time.LocalDateTime;
import java.util.List;

import com.ecom.model.inventory.Specs;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	
	private String orderId;
	private String paymentMethod;
	private double totalAmount;
	private LocalDateTime createdAt;
	private String status;  

	public Payment() {
		
	}
	
	
	

	public Payment(String orderId, String paymentMethod, double totalAmount, String status) {
		super();
		this.orderId = orderId;
		this.paymentMethod = paymentMethod;
		this.totalAmount = totalAmount;
		this.createdAt = LocalDateTime.now();;
		this.status = status;
	}




	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
