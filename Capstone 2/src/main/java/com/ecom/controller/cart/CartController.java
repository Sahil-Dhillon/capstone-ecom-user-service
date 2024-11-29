package com.ecom.controller.cart;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.ecom.dto.cart.CartDto;
import com.ecom.dto.cart.CartItemDto;
import com.ecom.model.cart.CartItems;
import com.ecom.model.inventory.Category;
import com.ecom.service.cart.CartItemService;
import com.ecom.service.cart.CartService;
import com.ecom.service.cart.CartItemService;
import com.ecom.service.inventory.CategoryService;
@RestController
@RequestMapping("/cartitem")
public class CartController {
 
	@Autowired
    private CartService cartService;
 
    @PostMapping("/add")
    public ResponseEntity<CartItemDto> addItemToCart(
            @RequestParam int productId,
            @RequestParam int quantity,
            @RequestParam String variations,
            @RequestParam String userId) {
        CartItemDto cartItem = cartService.addItemToCart(productId, quantity, variations, userId);
        return ResponseEntity.ok(cartItem);
    }
 
    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable String userId) {
        CartDto cart = cartService.getCart(userId);
        return ResponseEntity.ok(cart);
    }
    @DeleteMapping("/deleteitem")
    public ResponseEntity<String> deleteCartItem(@RequestBody CartItems cartItem,@RequestParam int cartid) {
        String responseMessage = cartService.deleteCartItem(cartItem,cartid);
        return ResponseEntity.ok(responseMessage);
    }
    @PutMapping("/updateitem")
    public ResponseEntity<CartItemDto> updateCartItem(
            @RequestParam int cartid,
            @RequestBody CartItems updatedCartItem) {
        CartItemDto updatedItem = cartService.updateCartItem(cartid, updatedCartItem);
        return ResponseEntity.ok(updatedItem);
    }
}