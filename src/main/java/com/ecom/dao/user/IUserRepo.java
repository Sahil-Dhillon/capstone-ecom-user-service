package com.ecom.dao.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.user.UserDetails;

public interface IUserRepo extends JpaRepository<UserDetails, String>{
	Optional<UserDetails> findByEmail(String email);
	Optional<UserDetails> findByUserId(String userId);
	UserDetails findByVerificationToken(String token);
}

