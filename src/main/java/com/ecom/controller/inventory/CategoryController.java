package com.ecom.controller.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.model.inventory.Category;

import com.ecom.service.inventory.CategoryService;



@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
    private CategoryService service;
	@PostMapping("/add")
	public Category add(@RequestBody Category category) {
		return service.addCategory(category);
	}
	@GetMapping
	public List<Category> listAllCategory(){
		return service.listAllCategory();
	}
	@GetMapping("/{category_id}")
	public Category findById(@PathVariable(value = "category_id") Integer subCategoryId) {
		return service.findByCategoryId(subCategoryId);
	}
	
}
