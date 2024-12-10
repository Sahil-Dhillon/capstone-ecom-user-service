package com.ecom.dao.inventory;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.inventory.Category;
import com.ecom.model.inventory.Products;
import com.ecom.model.inventory.Subcategory;

public interface IProductRepo extends JpaRepository<Products, Integer> {

	List<Products> findByVendorId(String VendorId);
	Products findByProductId(Integer productId);
	List<Products> findByBrand(String brand);
	List<Products> findByPriceBetween(Integer lowerLimit, Integer upperLimit);
	
	Page<Products> findAll(Pageable p);
	List<Products> findByTagsContaining(String tags, Pageable p);
	Page<Products> findBySubCategory(Subcategory subcategory, Pageable p);
	List<Products> findByStatus(String string);
	
	
}
