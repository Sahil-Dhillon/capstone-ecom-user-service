package com.ecom.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dao.user.IUserRepo;
import com.ecom.dto.UpdateUserDto;
import com.ecom.model.inventory.Category;
import com.ecom.model.user.Counter;
import com.ecom.model.user.UserAddresses;
import com.ecom.model.user.UserDetails;
import com.ecom.service.inventory.CategoryService;
import com.ecom.service.user.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    
    @Autowired
    private IUserRepo userRepo;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDetails> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
        UserDetails currentUser = (UserDetails) authentication.getPrincipal();
        
        UserDetails user = userService.findByUsername(currentUser.getUsername());
        
        return ResponseEntity.ok(user);
    }
    
    @PutMapping("/me")
    public ResponseEntity<UserDetails> updateUser(@RequestBody UpdateUserDto newUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails) authentication.getPrincipal();
        UserDetails user = userService.updateUser(currentUser.getUsername(),newUser); 
        return ResponseEntity.ok(user);
    }
    
    @PutMapping("/me/addresses")
    public ResponseEntity<UserDetails> updateUserAddresses(@RequestBody List<UserAddresses> addresses) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        UserDetails currentUser = (UserDetails) authentication.getPrincipal();
        UserDetails user = userService.updateUserAddresses(currentUser.getUsername(), addresses);
        return ResponseEntity.ok(user);
    }
    
    
    @GetMapping("/counts")
    public Counter counts() {
    	return userService.getCounts();
    }
    
    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        UserDetails user = userRepo.findByVerificationToken(token);
        if(user==null)
        	 return ResponseEntity.badRequest().body("Not found User.");
        if (user.isEmailVerified()) {
            return ResponseEntity.badRequest().body("Email is already verified.");
        }
        System.out.println("Inside verification mail controller"+user);
        // Mark email as verified
        if(!user.getVerificationToken().equals(token)) {
        	user.setEmailVerified(false);
        	return ResponseEntity.ok("Invalid Token");
        }
        user.setEmailVerified(true);
        user.setVerificationToken(null); // Clear the token
        userRepo.save(user);
        return ResponseEntity.ok("Email verified successfully! You can now log in.");
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDetails>> allUsers() {
        List <UserDetails> users = userService.allUsers();
        System.out.println(users);
        return ResponseEntity.ok(users);
    }
}