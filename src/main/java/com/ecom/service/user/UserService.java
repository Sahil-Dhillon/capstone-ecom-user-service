package com.ecom.service.user;


import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecom.dao.inventory.ICategoryRepo;
import com.ecom.dao.user.IUserRepo;
import com.ecom.dto.LoginUserDto;
import com.ecom.dto.RegisterUserDto;
import com.ecom.model.inventory.Category;
import com.ecom.model.user.UserDetails;


//@Service
//public final class UserService {
//	
//	@Autowired
//	private IUserRepo userRepository;
//	
//	public UserDetails addCategory(UserDetails user) {
//		return userRepository.saveAndFlush(user);
//	}
//	
//	public UserDetails registerUser(UserDetails user) {
//		long timestamp = Instant.now().toEpochMilli();
//    	String userId= user.getFirstName().substring(0, 2).toUpperCase()+String.valueOf(timestamp).substring(String.valueOf(timestamp).length() - 4)+user.getLastName().substring(0, 2).toUpperCase();
//		user.setUserId(userId);
//    	return addCategory(user);
//	}
//	
//	public UserDetails login(String email,String password) {
//		Optional<UserDetails> user=userRepository.findByEmail(email);
//		if (user.isPresent()) {
//            UserDetails userDetails = user.get();
//            if (userDetails.getPassword().equals(password)) { // You may need to hash passwords
//                return userDetails;
//            }
//        }
//        return null;
//		
//	}
//	public UserDetails findById(String userId) {
//		Optional<UserDetails> user=userRepository.findByUserId(userId);
//		if (user.isPresent()) {
//            UserDetails userDetails = user.get();
//            return userDetails;
//        }
//        return null;
//	}
//	
//	
//
//	
//}

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final IUserRepo userRepository;    
	
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;
    
    

    public UserService(
        IUserRepo userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public Collection<GrantedAuthority> mapRolesToAuthorities(List<String> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.toUpperCase()))
                .collect(Collectors.toList());
    }

    public UserDetails signup(RegisterUserDto input) {
    	UserDetails user=new UserDetails();
    	long timestamp = Instant.now().toEpochMilli();
    	System.out.println(passwordEncoder.encode(input.getPassword()));
    	user.setEmail(input.getEmail());
    	user.setFirstName(input.getFirstName());
    	user.setLastName(input.getLastName());
    	user.setMobile(input.getMobile());
    	String userId= user.getFirstName().substring(0, 2).toUpperCase()+String.valueOf(timestamp).substring(String.valueOf(timestamp).length() - 4)+user.getLastName().substring(0, 2).toUpperCase();
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setUserId(userId);
        
        String roles = input.getRoles();
        
        user.setRoles(roles);
        
        System.out.println(input.getRoles());
        
        
        return userRepository.save(user);
    }

    public UserDetails authenticate(LoginUserDto input) {
    	UserDetails user = userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
    	Collection<GrantedAuthority> authorities = mapRolesToAuthorities(List.of(user.getRoles()));
    	System.out.println(authorities);
    	System.out.println("---------------------");
    	 authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword(),authorities
                )
        );
    	
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
    
    public List<UserDetails> allUsers() {
        List<UserDetails> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);
        userRepository.findAll().forEach(System.out::println);
        return users;
    }
}
