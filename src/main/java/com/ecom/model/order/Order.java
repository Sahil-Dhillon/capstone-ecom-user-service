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

public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	private String userId;
	private String orderStatus;
	private double totalAmount;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String offerId;  //coupon
	private String vendorId;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "orderItemId", fetch = FetchType.EAGER)
	private List<OrderItem> listOfOrderItem;
	
	public Order() {
		
	}
	
	
	public Order(String userId, String orderStatus, double totalAmount, String offerId, String vendorId,List<OrderItem> listOfOrderItem) {
		super();
		this.userId = userId;
		this.orderStatus = orderStatus;
		this.totalAmount = totalAmount;
		this.offerId = offerId;
		this.vendorId = vendorId;
		this.createdAt= LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.listOfOrderItem=listOfOrderItem;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

}
