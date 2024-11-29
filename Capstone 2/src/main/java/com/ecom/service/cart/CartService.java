package com.ecom.service.cart;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.ecom.dao.cart.ICartItemsRepo;
import com.ecom.dao.cart.ICartRepo;
import com.ecom.dao.user.IUserRepo;
import com.ecom.dto.cart.CartDto;
import com.ecom.dto.cart.CartItemDto;
import com.ecom.model.cart.Cart;
import com.ecom.model.cart.CartItems;
import com.ecom.model.user.UserDetails;
 
@Service
public class CartService {
 
    @Autowired
    private IUserRepo userRepo;
 
    @Autowired
    private ICartRepo cartRepo;
 
    @Autowired
    private ICartItemsRepo cartItemsRepo;
 
    /**
     * Adds an item to the user's cart.
     *
     * @param productId  the product ID
     * @param quantity   the quantity of the product
     * @param variations product variations (e.g., color, size)
     * @param userId     the user's ID
     * @return the created or updated CartItemDTO
     */
    public CartItemDto addItemToCart(int productId, int quantity, String variations, String userId) {
        UserDetails user = userRepo.getById(userId);
        System.out.println("User is : "+userId );
        if(user==null) {
        	 throw new IllegalStateException("User not found for user ID: " + userId);
        }
        Cart cart = user.getUserCart();
        if (cart == null) {
            // If the user doesn't have a cart, create one
            cart = new Cart();
            cart.setCreatedAt(LocalDateTime.now());
            cart.setUpdatedAt(LocalDateTime.now());
            user.setUserCart(cart);
            cartRepo.save(cart);
            userRepo.save(user);
        }
 
        List<CartItems> cartItemsList = cart.getListOfCartItems();
 
        // Check if the product with the given variations already exists in the cart
        CartItems matchingCartItem = cartItemsList.stream()
                .filter(item -> item.getProductId() == productId &&
                                item.getVariations().equalsIgnoreCase(variations))
                .findFirst()
                .orElse(null);
 
        if (matchingCartItem != null) {
            // Update quantity if the item already exists
            matchingCartItem.setQuantity(matchingCartItem.getQuantity() + quantity);
            matchingCartItem.setCart(cart);
        } else {
            // Add new cart item
            matchingCartItem = new CartItems();
            matchingCartItem.setProductId(productId);
            matchingCartItem.setQuantity(quantity);
            matchingCartItem.setVariations(variations);
            matchingCartItem.setCart(cart);
            cartItemsList.add(matchingCartItem);
        }
 
        cart.setListOfCartItems(cartItemsList);
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepo.save(cart);
 
        return mapToCartItemDTO(matchingCartItem);
    }
 
    /**
     * Maps a CartItems entity to a CartItemDTO.
     *
     * @param cartItem the CartItems entity
     * @return the CartItemDTO
     */
    private CartItemDto mapToCartItemDTO(CartItems cartItem) {
    	System.out.println("Cart Item Mapping to Dto  is "+cartItem);
        CartItemDto dto = new CartItemDto();
        dto.setCartItemId(cartItem.getCartItemId());
        dto.setProductId(cartItem.getProductId());
        dto.setQuantity(cartItem.getQuantity());
        dto.setVariations(cartItem.getVariations());
        return dto;
    }
 
    /**
     * Retrieves the cart for a user.
     *
     * @param userId the user's ID
     * @return the CartDTO
     */
    public CartDto getCart(String userId) {
        UserDetails user = userRepo.getById(userId);
 
        Cart cart = user.getUserCart();
        if (cart == null) {
            throw new IllegalStateException("Cart not found for user ID: " + userId);
        }
 
        return mapToCartDto(cart);
    }
 
    /**
     * Maps a Cart entity to a CartDTO.
     *
     * @param cart the Cart entity
     * @return the CartDTO
     */
    private CartDto mapToCartDto(Cart cart) {
        CartDto dto = new CartDto();
        dto.setCartId(cart.getCartId());
        dto.setCartItems(cart.getListOfCartItems().stream()
                .map(this::mapToCartItemDTO)
                .collect(Collectors.toList()));
        return dto;
    }
    public String deleteCartItem(CartItems cartItem,int cartId) {
        // Fetch the cart by cartId
    	System.out.println(cartRepo.getById(cartId));
    	//System.out.println(cartItem.getCart());
        Cart cart = cartRepo.getById(cartId);
                if(cart==null) {throw new IllegalArgumentException("Cart not found with ID: " + cartItem.getCart().getCartId());}
 
        // Find and remove the cartItem from the cart's list
        CartItems itemToRemove = cart.getListOfCartItems().stream()
                .filter(item -> item.getCartItemId() == cartItem.getCartItemId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("CartItem not found in Cart with ID: " + cartItem.getCartItemId()));
 
        // Remove the cartItem from the list
        cart.getListOfCartItems().remove(itemToRemove);
 
        // Save the updated cart
        cartRepo.saveAndFlush(cart);
 
        // Delete the CartItem from the CartItems table
        cartItemsRepo.delete(itemToRemove);
 
        return "CartItem successfully deleted";
    }
    public CartItemDto updateCartItem(int cartId, CartItems cartItemGiven) {
    	int cartItemId=cartItemGiven.getCartItemId();
    	int productId=cartItemGiven.getProductId();
    	int quantity=cartItemGiven.getQuantity();
    	String variations=cartItemGiven.getVariations();
        // Fetch the cart by cartId
        Cart cart = cartRepo.getById(cartId);
        if(cart==null) {throw new IllegalArgumentException("Cart not found with ID: " + cartId);}
 
        // Check if the cartItem exists in the cart
        CartItems cartItem = cart.getListOfCartItems().stream()
                .filter(item -> item.getCartItemId() == cartItemId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("CartItem not found in Cart with ID: " + cartId));
 
        // Update the fields
        cartItem.setProductId(productId);
        cartItem.setQuantity(quantity);
        cartItem.setVariations(variations);
 
        // Save and flush the updated cartItem
        cartItemsRepo.saveAndFlush(cartItem);
 
        // Optionally save the cart as well
        cartRepo.saveAndFlush(cart);
 
        return mapToCartItemDTO(cartItem);
    }
}