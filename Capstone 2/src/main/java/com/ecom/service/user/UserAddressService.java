package com.ecom.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.user.IUserAddressesRepo;
import com.ecom.model.user.UserAddresses;

@Service
public class UserAddressService {
	private final IUserAddressesRepo addressRepository;

    public UserAddressService(IUserAddressesRepo addressRepository) {
        this.addressRepository = addressRepository;
    }

    public UserAddresses updateAddress(UserAddresses updatedAddressDetails) {
//    	UserAddresses address = addressRepository.findById(addressId)
//                .orElseThrow(() -> new RuntimeException("Address not found with ID: " + addressId));

        // Update the fields
//        address.setStreet(updatedAddressDetails.getStreet());
//        address.setCity(updatedAddressDetails.getCity());
//        address.setState(updatedAddressDetails.getState());
//        address.setZip(updatedAddressDetails.getZip());

        // Save and return the updated address
        return addressRepository.save(updatedAddressDetails);
    }
    
    public UserAddresses getAddress(Integer id) {
    	UserAddresses address = addressRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Address not found with ID: " + id));
    	return address;
    }
}
