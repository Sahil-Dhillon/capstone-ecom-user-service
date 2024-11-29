package com.ecom.model.inventory;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import com.ecom.model.cart.Cart;
import com.ecom.model.user.UserAddresses;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder.Default;


@Entity
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	private String vendorId;
	private String name;
	private String brand;
	private String description;
	private String tags;
	private List<String> variations;
	
//	@OneToOne(cascade = CascadeType.ALL, mappedBy = "categoryId", fetch = FetchType.EAGER)
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "category_id", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Category category;
	
	
//	@OneToOne(cascade = CascadeType.ALL, mappedBy = "subcategoryId", fetch = FetchType.EAGER)
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "subcategory_id", nullable = true)
	private Subcategory subCategory;
	 

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "specsId", fetch = FetchType.EAGER)
	private List<Specs> listOfSpecs;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewId", fetch = FetchType.EAGER)
	private List<Reviews> listOfReviews;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "imgId", fetch = FetchType.EAGER)
	private List<ImageGallery> listOfImages;
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public List<Reviews> getListOfReviews() {
		return listOfReviews;
	}

	public void setListOfReviews(List<Reviews> listOfReviews) {
		this.listOfReviews = listOfReviews;
	}

	public List<ImageGallery> getListOfImages() {
		return listOfImages;
	}

	public void setListOfImages(List<ImageGallery> listOfImages) {
		this.listOfImages = listOfImages;
	}

	private int price;
	private int quantity;
	private String profileImgUrl;
//	@Value("true")
	@Column(columnDefinition = "boolean default true")
	private boolean isAvailable;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	
	
	
    public Products() {
        // JPA requires a no-argument constructor for instantiation
    }
	
	public Products( String vendorId, String name, String brand, String description,
			Category category, Subcategory subCategory,List<Specs> listOfSpecs, int price, int quantity,
			String profileImgUrl, boolean isAvailable,String tags,List<String> variations) {
		super();
		
		this.vendorId = vendorId;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.category = category;
		this.subCategory = subCategory;
		this.listOfSpecs = listOfSpecs;
		this.price = price;
		this.quantity = quantity;
		this.profileImgUrl = profileImgUrl;
		this.isAvailable = isAvailable;
		this.tags=tags;
		this.variations=variations;
		
	}
	
	
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category= category;
	}
	public Subcategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(Subcategory subCategory) {
		this.subCategory = subCategory;
	}
	public List<Specs> getListOfSpecs() {
		return listOfSpecs;
	}
	public void setListOfSpecs(List<Specs> listOfSpecs) {
		this.listOfSpecs = listOfSpecs;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProfileImgUrl() {
		return profileImgUrl;
	}
	public void setProfileImgUrl(String profileImgUrl) {
		this.profileImgUrl = profileImgUrl;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDate getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public List<String> getVariations() {
		return variations;
	}

	public void setVariations(List<String> variations) {
		this.variations = variations;
	}
	
	
	
	
	
	
}