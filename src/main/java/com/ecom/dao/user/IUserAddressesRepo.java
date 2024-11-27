package com.ecom.dao.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.user.UserAddresses;
import com.ecom.model.user.UserDetails;

public interface IUserAddressesRepo extends JpaRepository<UserAddresses, Integer> {
	
//	Optional<UserAddresses> findByEmail(String email);
//	Optional<UserAddresses> findByUserId(String userId);

}
