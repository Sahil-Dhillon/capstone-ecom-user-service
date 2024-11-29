package com.ecom.dao.cart;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.ecom.model.cart.Wishlist;
 
public interface IWishlistRepo extends JpaRepository<Wishlist, Integer>{
 
}