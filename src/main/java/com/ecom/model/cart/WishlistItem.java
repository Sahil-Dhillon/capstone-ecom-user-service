package com.ecom.model.cart;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Data
@AllArgsConstructor
 
public class WishlistItem {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wishlistItemId;
	private int productId;
//	private Wishlist wishlist;
	public WishlistItem() {
	}
	public WishlistItem (int productId) {
		this.productId=productId;
	}
}
