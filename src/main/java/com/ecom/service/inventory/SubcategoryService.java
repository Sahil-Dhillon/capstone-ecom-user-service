package com.ecom.service.inventory;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.inventory.ICategoryRepo;
import com.ecom.dao.inventory.ISubcategoryRepo;
import com.ecom.model.inventory.Category;
import com.ecom.model.inventory.Subcategory;


@Service
public final class SubcategoryService {
	
	@Autowired
	private ISubcategoryRepo subcategoryRepository;
	
	@Autowired
	private ICategoryRepo categoryRepository;
	
	public Subcategory addSubcategory(Integer categoryId,Subcategory subcategory) {
		Category category=categoryRepository.findByCategoryId(categoryId);
		if(category!=null)
			System.out.println(category);
			subcategory.setCategory(category);
		
		return subcategoryRepository.saveAndFlush(subcategory);
	}
	
//	public String addMultipleSubcategory(Integer categoryId,List<Subcategory> subcategory) {
//		for(Subcategory s: subcategory) {
//			Subcategory sub = addSubcategory(categoryId, subcategory)
//		}
//	}
	
	public List<Subcategory> listAllSubcategory(){
		return subcategoryRepository.findAll();
	}
	
	public Subcategory findBySubcategoryId(Integer id) {
		return subcategoryRepository.findBySubcategoryId(id);
	}
	
	public Subcategory findByName(String name) {
		return subcategoryRepository.findByName(name);
	}
	
	public List<Subcategory> findByCategory(Category category) {
		return subcategoryRepository.findByCategory(category);
	}
	
	public List<Subcategory> findByCategoryName(String name) {
		Category category = categoryRepository.findByName(name);
		return findByCategory(category);
	}
	
	

	
}
