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
import com.ecom.model.inventory.Subcategory;
import com.ecom.service.inventory.CategoryService;
import com.ecom.service.inventory.SubcategoryService;



@RestController
@RequestMapping("/subcategory")
public class SubcategoryController {
	@Autowired
    private SubcategoryService service;
	
	@PostMapping("/add/{category_id}")
	public Subcategory add(@PathVariable(value = "category_id") Integer CategoryId,@RequestBody Subcategory subcategory) {
		return service.addSubcategory(CategoryId,subcategory);
	}
	
//	@PostMapping("/addMultiple/{category_id}")
//	public List<Subcategory> addMultiple(@PathVariable(value = "category_id") Integer CategoryId,@RequestBody List<Subcategory> subcategory) {
//		return service.addMultipleSubcategory(CategoryId,subcategory);
//	}
	
	@GetMapping
	public List<Subcategory> listAllSubCategory(){
		return service.listAllSubcategory();
	}
	@GetMapping("/{subcategory_id}")
	public Subcategory findById(@PathVariable(value = "subcategory_id") Integer subCategoryId) {
		return service.findBySubcategoryId(subCategoryId);
	}
	
	@GetMapping("/name/{subcategory_name}")
	public Subcategory findById(@PathVariable(value = "subcategory_name") String name) {
		return service.findByName(name);
	}
	
	@GetMapping("/byCategory")
	public List<Subcategory> findByCategory(@RequestBody Category category) {
		return service.findByCategory(category);
	}

	@GetMapping("/byCategoryName/{category_name}")
	public List<Subcategory> findByCategoryName(@PathVariable(value = "category_name") String categoryName) {
		return service.findByCategoryName(categoryName);
	}
	
}
