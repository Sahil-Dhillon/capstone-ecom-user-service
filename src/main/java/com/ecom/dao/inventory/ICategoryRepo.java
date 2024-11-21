package com.ecom.dao.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.inventory.Category;
import com.ecom.model.inventory.Subcategory;

public interface ICategoryRepo extends JpaRepository<Category, Integer>{
	public Category findByCategoryId(Integer id);
}
