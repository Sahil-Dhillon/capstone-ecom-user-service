package com.ecom.model.order;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderItemId;
	private int productId;
	private int price;
	private int quantity;
	private String variations;
	private int vendorId;

	public OrderItem() {

	}

	public OrderItem(int productId, int price, int quantity, String variations,int vendorId) {
		super();
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
		this.variations = variations;
		this.vendorId=vendorId;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getVariations() {
		return variations;
	}

	public void setVariations(String variations) {
		this.variations = variations;
	}

}
