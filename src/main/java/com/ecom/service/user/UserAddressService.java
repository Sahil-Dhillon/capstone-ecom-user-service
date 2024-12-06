package com.ecom.service.user;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.user.IUserAddressesRepo;
import com.ecom.dao.user.IUserRepo;
import com.ecom.dto.order.OrderDto;
import com.ecom.model.order.Order;
import com.ecom.model.order.OrderItem;
import com.ecom.model.order.Payment;
import com.ecom.model.user.UserAddresses;
import com.ecom.model.user.UserDetails;

@Service
public class UserAddressService {
	private final IUserAddressesRepo addressRepository;
	private final IUserRepo userRepository;   

    public UserAddressService(IUserAddressesRepo addressRepository, IUserRepo userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }
    
    public UserAddresses getAddress(Integer id) {
    	UserAddresses address = addressRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Address not found with ID: " + id));
    	return address;
    }
    
    public UserDetails addAddress(UserAddresses address, String email) {
		UserDetails user=userRepository.findByEmail(email).get();
		List<UserAddresses> addressList=user.getListOfUserAdresses();
		System.out.println("Address List is "+addressList);
		user.getListOfUserAdresses().add(address);
		userRepository.saveAndFlush(user);
		return user;
	}
    
    public UserDetails updateAddress(UserAddresses updatedAddress, String email) {
        // Find the user by email
        UserDetails user = userRepository.findByEmail(email).orElseThrow(() -> 
            new RuntimeException("User not found with email: " + email)
        );

        // Get the list of user addresses
        List<UserAddresses> addressList = user.getListOfUserAdresses();

        // Find the address to update by ID
        UserAddresses addressToUpdate = addressList.stream()
            .filter(address -> address.getAddressId() == updatedAddress.getAddressId())
            .findFirst()
            .orElseThrow(() -> 
                new RuntimeException("Address not found with ID: " + updatedAddress.getAddressId())
            );

        // Update the fields of the existing address
        addressToUpdate.setAddressLine1(updatedAddress.getAddressLine1());
        addressToUpdate.setAddressLine2(updatedAddress.getAddressLine2());
        addressToUpdate.setCity(updatedAddress.getCity());
        addressToUpdate.setState(updatedAddress.getState());
        addressToUpdate.setPincode(updatedAddress.getPincode());
        addressToUpdate.setAddressMobile(updatedAddress.getAddressMobile());
        addressToUpdate.setCountry(updatedAddress.getCountry());
        addressToUpdate.setLabel(updatedAddress.getLabel());
//        addressToUpdate.setIs_primary(updatedAddress.get);
        addressToUpdate.setRecepientName(updatedAddress.getRecepientName());

        // Save and flush the updated user
        userRepository.saveAndFlush(user);
        
        return user;
    }

    
}
