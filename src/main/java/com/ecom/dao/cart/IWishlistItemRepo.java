package com.ecom.dao.cart;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.ecom.model.cart.WishlistItem;
 
public interface IWishlistItemRepo extends JpaRepository<WishlistItem, Integer>{
 
}
