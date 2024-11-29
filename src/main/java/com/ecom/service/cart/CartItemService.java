package com.ecom.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.cart.ICartItemsRepo;
import com.ecom.dao.cart.ICartRepo;
import com.ecom.dao.user.IUserRepo;
import com.ecom.model.cart.Cart;
import com.ecom.model.cart.CartItems;
import com.ecom.model.user.UserDetails;

@Service
public class CartItemService {

	@Autowired
	private ICartItemsRepo cartItemsRepository;
	@Autowired
	private IUserRepo userRepo;
	@Autowired
	private ICartRepo cartRepo;

	public CartItems createCartItem(int productId, int quantity, String variations) {
		CartItems cartItem=cartItemsRepository.findByProductId(productId);
		if(cartItem.getVariations().equalsIgnoreCase(variations)) {
			cartItem.setQuantity(cartItem.getQuantity()+quantity);
			
		}
		else {		
		 cartItem = new CartItems(productId, quantity, variations);
		}
		return cartItemsRepository.saveAndFlush(cartItem);

	}

	public String deleteCartItem(int cartItemId) {
		cartItemsRepository.deleteById(cartItemId);
		return "item deleted";
	}
	
	public CartItems updateCartItem(CartItems cartItem,String userId) {
		int cartId=cartItem.getCartItemId();
		int productId=cartItem.getCartItemId();
		String variations=cartItem.getVariations();
		
		CartItems result=null;
		
		CartItems existingCartItem = cartItemsRepository.getById(cartId);
		if(!cartItem.getVariations().equalsIgnoreCase(existingCartItem.getVariations())) {
			
			UserDetails userCheck= userRepo.getById(userId);
			Cart cartCheck = cartRepo.getById(userCheck.getUserCart().getCartId());
			List<CartItems> ListOfCartItems=cartCheck.getListOfCartItems();
			
			result = ListOfCartItems.stream()
				    .filter(cartItems -> cartItem.getProductId() == productId && cartItem.getVariations().equals(variations))
				    .findFirst()
				    .orElse(null);
			
			
			cartItemsRepository.deleteById(cartItem.getCartItemId());
			
			
			result.setQuantity(cartItem.getQuantity());
			result.setVariations(cartItem.getVariations());
			
		}
		
		cartItem.setQuantity(cartItem.getQuantity());
		
		if(result==null)return cartItemsRepository.saveAndFlush(existingCartItem);
		else return cartItemsRepository.saveAndFlush(result);

	}
	
	

}
