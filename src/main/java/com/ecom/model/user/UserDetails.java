package com.ecom.model.user;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;

import com.ecom.model.cart.Cart;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails{

	@Id
	@Column(nullable = false)
	private String userId;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = true)
	private String lastName;
	@Column(unique = true, length = 100, nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = true)
	private String mobile;
	@Column(nullable = true)
//	@ElementCollection(fetch = FetchType.EAGER)
    private String roles; // List of roles (e.g., ["ADMIN", "USER"])
	@CreationTimestamp
    @Column(updatable = false, name = "created_at")
	private LocalDate createdAt;
	@UpdateTimestamp
    @Column(name = "updated_at")
	private LocalDate updatedAt;
	@Column(nullable = true)
	private String profileImg;
	
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id") // This maps the relationship
    private List<UserAddresses> listOfUserAdresses;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Cart userCart;
	
    
    
    
	public UserDetails() {
		super();
		this.roles = "USER";
	}

	public UserDetails(String firstName, String lastName, String email, String password, String mobile, String roles) {
		super();
    	
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.roles = roles;
		
	}
	public UserDetails(String email, String password) {
		super();
		this.email = email;
		this.password = password;
				
	}
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
	
	

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

	public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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


	public List<UserAddresses> getListOfUserAdresses() {
		return listOfUserAdresses;
	}


	public void setListOfUserAdresses(List<UserAddresses> listOfUserAdresses) {
		this.listOfUserAdresses = listOfUserAdresses;
	}


	public Cart getUserCart() {
		return userCart;
	}


	public void setUserCart(Cart userCart) {
		this.userCart = userCart;
	}
    
    
	
	
	
}
