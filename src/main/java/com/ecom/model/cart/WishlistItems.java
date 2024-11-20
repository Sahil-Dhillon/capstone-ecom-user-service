package com.ecom.model.cart;


import java.util.Set;

import com.ecom.model.user.UserAddresses;
import com.ecom.model.user.UserDetails.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class WishlistItems {

	@Id
	private String wishlistItemId;
	private String productId;
	
}
