package com.ecom.model.user;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class UserAddresses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
 
	private String recepientName;
	private String addressMobile;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String pincode;
	private String state;
	private String country;
	private Label label;
	private boolean is_primary;
	
    public static enum Label {
        OFFICE,
        HOME,
        OTHER;
    }

    
    
    
	public UserAddresses(String recepientName, String addressMobile, String addressLine1, String addressLine2,
			String city, String pincode, String state, String country, Label label) {
		super();
    
		this.recepientName = recepientName;
		this.addressMobile = addressMobile;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
		this.country = country;
		this.label = label;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getRecepientName() {
		return recepientName;
	}

	public void setRecepientName(String recepientName) {
		this.recepientName = recepientName;
	}

	public String getAddressMobile() {
		return addressMobile;
	}

	public void setAddressMobile(String addressMobile) {
		this.addressMobile = addressMobile;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public boolean isIs_primary() {
		return is_primary;
	}

	public void setIs_primary(boolean is_primary) {
		this.is_primary = is_primary;
	}
	
}
