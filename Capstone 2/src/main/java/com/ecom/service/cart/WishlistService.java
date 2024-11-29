package com.ecom.service.cart;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.ecom.dao.cart.IWishlistItemRepo;

import com.ecom.dao.cart.IWishlistRepo;
import com.ecom.dao.user.IUserRepo;
import com.ecom.dto.cart.WishlistDto;
import com.ecom.model.cart.Wishlist;
import com.ecom.model.cart.WishlistItem;
import com.ecom.model.user.UserDetails;
 
@Service
public class WishlistService {
 
	@Autowired
	private IWishlistItemRepo wishlistRepository;
 
	@Autowired
	private IUserRepo userRepository; // Assuming you have a service to fetch user details
 
	public Set<WishlistItem> addToWishlist(String userId, int productId) {
		UserDetails user = userRepository.getById(userId);
 
		Set<WishlistItem> listOfWishListItems = user.getWishlistItems();
		WishlistItem item = new WishlistItem(productId);
		// Check if the productId already exists in the wishlist
		boolean productExists = listOfWishListItems.stream()
				.anyMatch(entry -> entry.getProductId() == productId);
 
		if (productExists) {
			System.out.println("Product with ID " + productId + " already exists in the wishlist.");
			return listOfWishListItems; // Return the existing wishlist if the product is already added
		}
		listOfWishListItems.add(item);
 
		userRepository.save(user);
		return listOfWishListItems;
 
	}
	public Set<WishlistItem> getWishlistByUserId(String userId) {
        // Fetch the user by userId
        UserDetails user = userRepository.getById(userId);
        if (user == null) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        Set<WishlistItem> wishlist = user.getWishlistItems();
        if (wishlist == null) {
            throw new RuntimeException("Wishlist not found for user with ID: " + userId);
        }
        return wishlist;
    }
	public String deleteItem(String userId,int productId) {
        // Fetch the user by userId
        UserDetails user = userRepository.getById(userId);
        if (user == null) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        Set<WishlistItem> wishlist = user.getWishlistItems();
        if (wishlist == null) {
            throw new RuntimeException("Wishlist not found for user with ID: " + userId);
        }
        Set<WishlistItem> updatedWishlist = wishlist.stream()
                .filter(item -> item.getProductId() != productId)
                .collect(Collectors.toSet());
        return productId+" deleted";
    }
}