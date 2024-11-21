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
	
	public Products addProduct(Integer subCategoryId,Products product) {
		if(product!=null) {
			
		    Subcategory subCategory = subcategoryRepository.findById(subCategoryId)
		            .orElseThrow(() -> new IllegalArgumentException("Invalid subcategory ID"));
		    
		    product.setCategory(subCategory.getCategory());
			product.setSubCategory(subCategory);
			productRepo.saveAndFlush(product);
		return product;
		}
		System.out.println("Failed to add product");
		return null;
	}
	
	public List<Products> listAllProducts(){
		return productRepo.findAll();
	}
	
	public List<Products> listAllByVendorId(String vendorId){
		return productRepo.findByVendorId(vendorId);
	}
	public Products listAllById(Integer id){
		return productRepo.getById(id);
	}
	public List<Products> listAllByBrand(String brand){
		return productRepo.findByBrand(brand);
	}
    
	public List<Products> listAllByPriceBetween(Integer lowerLimit,Integer upperLimit){
		return productRepo.findByPriceBetween(lowerLimit,upperLimit);
	}
	
}
