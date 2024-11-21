package com.ecom.service.user;


import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecom.dao.inventory.ICategoryRepo;
import com.ecom.dao.user.IUserRepo;
import com.ecom.model.inventory.Category;
import com.ecom.model.user.UserDetails;


@Service
public final class UserService {
	
	@Autowired
	private IUserRepo userRepository;
	
	public UserDetails addCategory(UserDetails user) {
		return userRepository.saveAndFlush(user);
	}
	
	public UserDetails registerUser(UserDetails user) {
		long timestamp = Instant.now().toEpochMilli();
    	String userId= user.getFirstName().substring(0, 2).toUpperCase()+String.valueOf(timestamp).substring(String.valueOf(timestamp).length() - 4)+user.getLastName().substring(0, 2).toUpperCase();
		user.setUserId(userId);
    	return addCategory(user);
	}
	
	public UserDetails login(String email,String password) {
		UserDetails user=userRepository.findByEmail(email);
		if(user!=null && user.getPassword().equals(password))
			return user;
		return null;
		
	}
	public UserDetails findById(String userId) {
		return userRepository.getById(userId);
	}
	
	

	
}
