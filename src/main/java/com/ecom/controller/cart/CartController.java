package com.ecom.controller.cart;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.controller.user.UserController;
import com.ecom.dto.cart.CartDto;
import com.ecom.dto.cart.CartItemDto;
import com.ecom.model.cart.CartItems;
import com.ecom.model.inventory.Category;
import com.ecom.model.inventory.Products;
import com.ecom.model.user.UserDetails;
import com.ecom.service.cart.CartItemService;
import com.ecom.service.cart.CartService;
import com.ecom.service.cart.CartItemService;
import com.ecom.service.inventory.CategoryService;
import com.ecom.service.inventory.ProductService;
import com.ecom.service.user.UserService;
@RestController
@RequestMapping("/cartitem")
public class CartController {
 
	@Autowired
    private CartService cartService;
 
//	@PutMapping
	private final UserService userService;
	

    public CartController(UserService userService) {
        this.userService = userService;
        
    }
	
    @PostMapping("/add")
    public ResponseEntity<CartItems> addItemToCart(
            @RequestParam int productId,
            @RequestParam int quantity,
            @RequestParam String variations) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails) authentication.getPrincipal();
        UserDetails user = userService.findByUsername(currentUser.getUsername()); 
        CartItems cartItem = cartService.addItemToCart(productId, quantity, variations, user.getUserId());
        return ResponseEntity.ok(cartItem);
    }
    
    @PutMapping("/emptyCart")
    public ResponseEntity<String> emptyCart() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails) authentication.getPrincipal();
        UserDetails user = userService.findByUsername(currentUser.getUsername()); 
        String response = cartService.emptyCart(user.getUserCart().getCartId());
        return ResponseEntity.ok(response);
    }
 
    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable String userId) {
        CartDto cart = cartService.getCart(userId);
        return ResponseEntity.ok(cart);
    }
    @DeleteMapping("/deleteitem")
    public ResponseEntity<String> deleteCartItem(@RequestParam int cartItemId) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails) authentication.getPrincipal();
        UserDetails user = userService.findByUsername(currentUser.getUsername()); 
        String responseMessage = cartService.deleteCartItem(cartItemId,user.getUserCart().getCartId());
        return ResponseEntity.ok(responseMessage);
    }
    @PutMapping("/updateitem")
    public ResponseEntity<CartItemDto> updateCartItem(
            @RequestBody CartItems updatedCartItem) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails) authentication.getPrincipal();
        UserDetails user = userService.findByUsername(currentUser.getUsername()); 
        CartItemDto updatedItem = cartService.updateCartItem(user.getUserCart().getCartId(), updatedCartItem);
        return ResponseEntity.ok(updatedItem);
    }
}
