package com.ecom.model.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ecom.model.cart.Cart;
import com.ecom.model.cart.WishlistItem;
import com.ecom.model.inventory.Specs;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "`order`")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	private String orderStatus;
	private double totalAmount;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String coupon; // coupon
	private int addressId;

	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	private Payment payment = new Payment();

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	private List<OrderItem> listOfOrderItems = new ArrayList<>();

	public Order() {
		
	}

	public Order(String coupon, String orderStatus, double totalAmount, List<OrderItem> listOfOrderItems) {
		super();
		
		this.orderStatus = orderStatus;
		this.totalAmount = totalAmount;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
        this.coupon=coupon;
        this.listOfOrderItems=listOfOrderItems;
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


}
