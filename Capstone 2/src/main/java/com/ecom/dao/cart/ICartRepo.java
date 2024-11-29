package com.ecom.dao.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.cart.Cart;
import com.ecom.model.inventory.Category;

public interface ICartRepo extends JpaRepository<Cart, Integer>{

	
}
