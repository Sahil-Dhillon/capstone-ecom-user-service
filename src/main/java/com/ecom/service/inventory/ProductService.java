package com.ecom.service.inventory;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.inventory.ICategoryRepo;
import com.ecom.dao.inventory.IProductRepo;
import com.ecom.dao.inventory.ISubcategoryRepo;
import com.ecom.model.inventory.Category;
import com.ecom.model.inventory.Products;
import com.ecom.model.inventory.Subcategory;

@Service
public final class ProductService {
	@Autowired
    private IProductRepo productRepo;
	@Autowired
	private ISubcategoryRepo subcategoryRepository;
	@Autowired
	private ICategoryRepo categoryRepository;
	
	public Products addProduct(Products product) {
		if(product!=null) {
//			Optional<Category> category = categoryRepository.findById(product.getCategoryId())
//		            .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
//
//		    Optional<Subcategory> subcategory = subcategoryRepository.findById(product.getSubCategoryId())
//		            .orElseThrow(() -> new IllegalArgumentException("Invalid subcategory ID"));
		productRepo.saveAndFlush(product);
		return product;
		}
		System.out.println("Failed to add product");
		return null;
	}
	
	public List<Products> listAllProducts(){
		return productRepo.findAll();
	}
	

	
}
