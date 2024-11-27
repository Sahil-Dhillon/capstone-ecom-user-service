package com.ecom.model.cart;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.ecom.model.user.UserAddresses;
import com.ecom.model.user.UserDetails.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cartItemId", fetch = FetchType.EAGER)
	private List<CartItems> listOfCartItems;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public Cart(List<CartItems> listOfCartItems, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.listOfCartItems = listOfCartItems;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public List<CartItems> getListOfCartItems() {
		return listOfCartItems;
	}
	public void setListOfCartItems(List<CartItems> listOfCartItems) {
		this.listOfCartItems = listOfCartItems;
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
