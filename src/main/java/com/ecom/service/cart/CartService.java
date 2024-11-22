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

	public CartItems createCartItem(int productId, int quantity, String variations) {
		CartItems cartItem = new CartItems(productId, quantity, variations);
		return cartItemsRepository.saveAndFlush(cartItem);

	}

	public String deleteCartItem(CartItems cartItem) {
		cartItemsRepository.delete(cartItem);
		return "item deleted";
	}
	
	public CartItems updateCartItem(int cartItemId,int quantity) {
		CartItems cartItem = cartItemsRepository.getById(cartItemId);
		cartItem.setQuantity(cartItem.getQuantity()+quantity);
		
		return cartItemsRepository.saveAndFlush(cartItem);

	}
	
	

}
