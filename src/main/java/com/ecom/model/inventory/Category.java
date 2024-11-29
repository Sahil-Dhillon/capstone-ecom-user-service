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
import lombok.Data;

@Data
@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	private String name;
	private String bannerImage;
	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.EAGER)
//	private Set<Subcategory> listOfSubcategory;
	
	public Category() {
		
	}
	
	public Category(String name, String bannerImage) {
		super();
		this.name = name;
		this.bannerImage = bannerImage;
		//this.listOfSubcategory = listOfSubcategory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/*
	 * public Set<Subcategory> getListOfSubcategory() { return listOfSubcategory; }
	 * public void setListOfSubcategory(Set<Subcategory> listOfSubcategory) {
	 * this.listOfSubcategory = listOfSubcategory; }
	 */
	
	
	
	
	
}