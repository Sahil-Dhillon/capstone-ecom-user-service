package com.ecom.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.model.inventory.Category;
import com.ecom.model.user.UserDetails;
import com.ecom.service.inventory.CategoryService;
import com.ecom.service.user.UserService;



@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
    private UserService service;
	
	@PostMapping("/register")
	public UserDetails register(@RequestBody UserDetails user) {
		return service.registerUser(user);
	}
	
	
	@GetMapping("/{user_id}")
	public UserDetails findById(@PathVariable(value = "user_id") String userId) {
		return service.findById(userId);
	}
	
	@PostMapping("/login")
	public UserDetails login(@RequestBody UserDetails user) {
		String email=user.getEmail();
		String password=user.getPassword();
		return service.login(email, password);
	}
	
	
}

