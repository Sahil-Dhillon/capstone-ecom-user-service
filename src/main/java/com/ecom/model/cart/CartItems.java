package com.ecom.model.cart;
 
import java.util.Set;
 
import com.ecom.model.user.UserAddresses;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Data
@AllArgsConstructor
public class CartItems {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartItemId;
 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id")
	@JsonIgnore
	private Cart cart;
 
	private int productId;
	private String name;
	private String brand;
	private String image;
	@Column(length = 1000)
	private String description;
	private int price;
	private int quantity;
	private String variations;
 
	public CartItems() {
 
	}
 
	public CartItems(int productId, int quantity, String variations) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.variations = variations;
	}
 
	public int getCartItemId() {
		return cartItemId;
	}
 
	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}
 
	public int getProductId() {
		return productId;
	}
 
	public void setProductId(int productId) {
		this.productId = productId;
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

 
	public Cart getCart() {
		return cart;
	}
 
	public void setCart(Cart cart) {
		this.cart = cart;
	}
 
	@Override
	public String toString() {
		return "CartItems [cartItemId=" + cartItemId + ", productId=" + productId + ", quantity=" + quantity
				+ ", variations=" + variations + "]";
	}
 
}