package com.ecom.model.inventory;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.ecom.model.cart.Cart;
import com.ecom.model.user.UserAddresses;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Subcategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subcategoryId;
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER,  optional = false) // You can use EAGER if required
	@JoinColumn(name = "category_id", nullable = false)
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	@JsonIgnore
    private Category category;
	
	public Subcategory() {
		
	}
	
	public Subcategory(String name) {
		super();
		this.name = name;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getSubcategoryId() {
		return subcategoryId;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
	
	
}
