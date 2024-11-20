package com.ecom.model.cart;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.ecom.model.user.UserAddresses;
import com.ecom.model.user.UserDetails.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Wishlist {

	@Id
	private String wishlistId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wishlistItemId", fetch = FetchType.EAGER)
	private List<WishlistItems> listOfWishlistItems;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	
}
