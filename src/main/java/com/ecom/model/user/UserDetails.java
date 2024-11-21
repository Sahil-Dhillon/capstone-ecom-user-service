package com.ecom.model.user;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

import com.ecom.model.cart.Cart;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class UserDetails {

	@Id
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String mobile;
	private Role role;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private String profileImg;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "addressId", fetch = FetchType.EAGER)
	private Set<UserAddresses> listOfUserAdresses;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Cart userCart;
	
    public static enum Role {
        ADMIN,
        VENDOR,
        USER;
    }


    
    
    
	public UserDetails() {
		super();
	}


	public UserDetails(String firstName, String lastName, String email, String password, String mobile, Role role) {
		super();
    	
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.role = role;
		
	}
	public UserDetails(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		
		
	}

	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
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


	public String getProfileImg() {
		return profileImg;
	}


	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}


	public Set<UserAddresses> getListOfUserAdresses() {
		return listOfUserAdresses;
	}


	public void setListOfUserAdresses(Set<UserAddresses> listOfUserAdresses) {
		this.listOfUserAdresses = listOfUserAdresses;
	}
    
    
	
	
	
}
