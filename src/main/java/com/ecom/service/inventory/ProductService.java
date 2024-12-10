package com.ecom.service.inventory;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		    product.setStatus("pending");
		    product.setAvailable(false);
			product.setSubCategory(subCategory);
			
			productRepo.saveAndFlush(product);
		return product;
		}
		System.out.println("Failed to add product");
		return null;
	}
	
	public List<Products> listAllProducts(int pageNumber,int pageSize,String sortBy,String sortDir){
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}else {
			sort=Sort.by(sortBy).descending();
		}
		Pageable p=(Pageable) PageRequest.of(pageNumber, pageSize,sort);
		Page<Products> pageContents =productRepo.findAll(p);
		List<Products> listofContentOnOnePage=pageContents.getContent();
		return listofContentOnOnePage;
		
	}
	
	public List<Products> listAllByVendorId(String vendorId){
		return productRepo.findByVendorId(vendorId);
	}

	public List<Products> listAllAddRequests(){
		return productRepo.findByStatus("pending");
	}
	public Products listById(Integer id){
		return productRepo.findByProductId(id);
	}
	public List<Products> listAllByBrand(String brand){
		return productRepo.findByBrand(brand);
	}
    
	public List<Products> listAllByPriceBetween(Integer lowerLimit,Integer upperLimit){
		return productRepo.findByPriceBetween(lowerLimit,upperLimit);
	}
	
	
	public List<Products> listAllProductsBySubcategoryId(int pageNumber,int pageSize,String sortBy,String sortDir,Subcategory subcategory){
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}else {
			sort=Sort.by(sortBy).descending();
		}
		Pageable p=(Pageable) PageRequest.of(pageNumber, pageSize,sort);
		Page<Products> pageContents =productRepo.findBySubCategory(subcategory,p);
		List<Products> listofContentOnOnePage=pageContents.getContent();
		return listofContentOnOnePage;
	}
	
	public List<Products> searchByTagUnderSubcategory(int pageNumber,int pageSize,String sortBy,String sortDir,String tags,int subcategoryId){
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}else {
			sort=Sort.by(sortBy).descending();
		}
		Pageable p=(Pageable) PageRequest.of(pageNumber, pageSize,sort);
		List<Products> pageContents = productRepo.findByTagsContaining(tags,p).stream().filter(item->item.getSubCategory().getSubcategoryId()==subcategoryId).collect(Collectors.toList());
		return pageContents;
	}

	public List<Products> searchByTag(int pageNumber,int pageSize,String sortBy,String sortDir,String tags){
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}else {
			sort=Sort.by(sortBy).descending();
		}
		Pageable p=(Pageable) PageRequest.of(pageNumber, pageSize,sort);
		List<Products> pageContents = productRepo.findByTagsContaining(tags,p);
		return pageContents;
	}
	
	public Products approveProduct(int productId) {
		Products product = productRepo.findByProductId(productId);
		product.setStatus("Accepted");
		return productRepo.saveAndFlush(product);
	}
	
	public Products rejectProduct(int productId) {
		Products product = productRepo.findByProductId(productId);
		product.setStatus("Rejected");
		return productRepo.saveAndFlush(product);
	}
}
