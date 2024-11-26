package com.ecom.dao.inventory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.inventory.Category;
import com.ecom.model.inventory.Subcategory;

public interface ISubcategoryRepo extends JpaRepository<Subcategory,Integer>{
	public Subcategory findBySubcategoryId(Integer id);
	public Subcategory findByName(String name);
	public List<Subcategory> findByCategory(Category category);
}
