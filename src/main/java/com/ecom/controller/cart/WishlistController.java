package com.ecom.controller.cart;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.ecom.dto.cart.WishlistDto;
import com.ecom.model.cart.Wishlist;
import com.ecom.model.cart.WishlistItem;
import com.ecom.service.cart.WishlistService;
 
@RestController
@RequestMapping("/wishlist")
public class WishlistController {
 
	@Autowired
	WishlistService wishlistService;
 
	@PostMapping("/add")
	public Set<WishlistItem> addToWishlist(@RequestParam String userId, @RequestParam int productId) {
 
		return wishlistService.addToWishlist(userId, productId);
 
	}
 
	@GetMapping("/{userId}")
	public Set<WishlistItem> getWishlistByUserId(@PathVariable(name = "userId") String userId) {
		return wishlistService.getWishlistByUserId(userId);
	}
	@DeleteMapping("/delete")
	public String getWishlistByUserId(@RequestParam String userId, @RequestParam int productId) {
		return wishlistService.deleteItem(userId, productId);
	}
 
}
