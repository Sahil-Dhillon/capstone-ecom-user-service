package com.ecom.dto;

import java.util.List;
import java.util.Set;

import com.ecom.model.user.UserAddresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String mobile;
    private String roles;
    private List<UserAddresses> userAddresses;
    
    
 
}