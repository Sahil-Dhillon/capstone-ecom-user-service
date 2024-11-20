package com.ecom.dao.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.inventory.Products;

public interface IProductRepo extends JpaRepository<Products, Integer> {
	
	
}
