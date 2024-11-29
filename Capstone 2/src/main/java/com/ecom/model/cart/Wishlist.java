package com.ecom.model.cart;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
 
import com.ecom.model.user.UserAddresses;

 
import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
 
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  wishlistId;
 
//	@ToString.Exclude 
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wishlistItemId", fetch = FetchType.LAZY)
	private List<WishlistItem> listOfWishlistItems=new ArrayList<>();
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;


}