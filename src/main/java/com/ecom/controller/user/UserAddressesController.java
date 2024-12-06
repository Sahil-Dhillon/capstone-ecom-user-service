package com.ecom.controller.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.model.user.UserAddresses;
import com.ecom.model.user.UserDetails;
import com.ecom.service.user.UserAddressService;
import com.ecom.service.user.UserService;

@RequestMapping("/users/me/addresses")
@RestController
public class UserAddressesController {

	private final UserAddressService addressService;

	public UserAddressesController(UserAddressService addressService) {
		this.addressService = addressService;
	}

	@PutMapping("/update")
	public ResponseEntity<UserDetails> updateAddress(@RequestBody UserAddresses updatedAddressDetails) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails currentUser = (UserDetails) authentication.getPrincipal();
		UserDetails updatedUser = addressService.updateAddress(updatedAddressDetails, currentUser.getUsername());
		return ResponseEntity.ok(updatedUser);
	}

	@PutMapping("/add")
	public ResponseEntity<UserDetails> addAdress(@RequestBody UserAddresses addressDetails) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails currentUser = (UserDetails) authentication.getPrincipal();
		UserDetails updatedUser = addressService.addAddress(addressDetails, currentUser.getUsername());
		return ResponseEntity.ok(updatedUser);
	}

//    @GetMapping("/{addressId}")
//    public ResponseEntity<UserAddresses> getAddress(
//            @PathVariable(name = "addressId") Integer addressId) {
//        UserAddresses updatedAddress = addressService.getAddress(addressId);
//        return ResponseEntity.ok(updatedAddress);
//    }
//    private final UserAddresses userAddresses;

//    @GetMapping("/me")
//    public ResponseEntity<UserAddresses> authenticatedUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        
//        UserDetails currentUser = (UserDetails) authentication.getPrincipal();
//
//        return ResponseEntity.ok(currentUser);
//    }

//    @GetMapping("/all")
//    public ResponseEntity<UserAddresses>> allUsers() {
//        List <UserDetails> users = userService.allUsers();
//        System.out.println(users);
//        return ResponseEntity.ok(users);
//    }
}
