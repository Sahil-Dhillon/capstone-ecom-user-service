package com.ecom.service.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.cart.ICartItemsRepo;
import com.ecom.dao.cart.ICartRepo;
import com.ecom.dao.inventory.ICategoryRepo;
import com.ecom.dao.inventory.IProductRepo;
import com.ecom.dao.inventory.ISubcategoryRepo;
import com.ecom.model.cart.CartItems;

@Service
public class CartService {

	@Autowired
	private ICartRepo cartRepository;


	
	

}
