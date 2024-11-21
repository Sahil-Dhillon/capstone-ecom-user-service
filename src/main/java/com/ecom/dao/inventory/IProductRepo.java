package com.ecom.dao.inventory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.inventory.Category;
import com.ecom.model.inventory.Products;

public interface IProductRepo extends JpaRepository<Products, Integer> {

	List<Products> findByVendorId(String VendorId);
	List<Products> findByProductId(Integer productId);
	List<Products> findByBrand(String brand);
	List<Products> findByPriceBetween(Integer lowerLimit, Integer upperLimit);
	
	
}
