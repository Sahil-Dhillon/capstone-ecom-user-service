package com.ecom.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.LoginResponse;
import com.ecom.dto.LoginUserDto;
import com.ecom.dto.RegisterUserDto;
import com.ecom.model.user.UserDetails;
import com.ecom.service.JwtService;
import com.ecom.service.user.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final UserService authenticationService;

    public AuthenticationController(JwtService jwtService, UserService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDetails> register(@RequestBody RegisterUserDto registerUserDto) {
    	System.out.println(registerUserDto);
        UserDetails registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody LoginUserDto loginUserDto) {
    	UserDetails user = authenticationService.findByUsername(loginUserDto.getEmail());
//        UserDetails user = authenticationService.findByUsername(currentUser.getUsername()); 
    	System.out.println(user);
        if(!user.isEmailVerified()) {
        	System.out.println("User not verified");
        	return ResponseEntity.ok("Please Verify Email");
        }
        UserDetails authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
