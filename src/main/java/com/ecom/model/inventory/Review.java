package com.ecom.model.inventory;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.ecom.model.cart.Cart;
import com.ecom.model.user.UserAddresses;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewId;
	private String review;
	//@Column(name = "user_id_")
	private String userId;
	private int rating;  //out of 5
	
	
	public Review(String review, String userId, int rating) {
		super();
		this.review = review;
		this.userId = userId;
		this.rating = rating;
	}
	
	
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	
}
