package com.ecom.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.user.UserDetails;

public interface IUserRepo extends JpaRepository<UserDetails, String>{

	UserDetails findByEmail(String email);

}

