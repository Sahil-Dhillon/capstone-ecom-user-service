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
public class Cart {

	@Id
	private String cartId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cartItemId", fetch = FetchType.EAGER)
	private List<CartItems> listOfCartItems;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	
}
