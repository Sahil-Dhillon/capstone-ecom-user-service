package com.ecom.model.inventory;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.ecom.model.cart.Cart;
import com.ecom.model.user.UserAddresses;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class ImageGallery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String imgId;
	private String imgUrl;
	
	
	public ImageGallery(String imgUrl) {
		super();
		this.imgUrl = imgUrl;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
	
	
	
}
