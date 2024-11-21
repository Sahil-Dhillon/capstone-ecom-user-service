package com.ecom.dao.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.inventory.Subcategory;

public interface ISubcategoryRepo extends JpaRepository<Subcategory,Integer>{
	public Subcategory findBySubcategoryId(Integer id);
}
