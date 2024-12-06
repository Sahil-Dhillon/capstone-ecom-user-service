package com.ecom.dto;

import java.util.List;

import com.ecom.model.user.UserAddresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
    private String password;
    private String firstName;
    private String lastName;
    private String mobile;
    private String profileImgUrl;
}
