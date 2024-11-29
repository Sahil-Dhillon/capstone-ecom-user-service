package com.ecom.controller.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.model.inventory.Category;

import com.ecom.service.inventory.CategoryService;



@RestController
@RequestMapping("/category")
//@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class CategoryController {
	@Autowired
    private CategoryService service;
	@PostMapping("/add")
	public Category add(@RequestBody Category category) {
		return service.addCategory(category);
	}
	
	@PostMapping("/addMultiple")
    public ResponseEntity<?> addMultipleCategories(@RequestBody List<Category> categories) {
        List<Category> savedCategories = service.saveAllCategories(categories);
        return ResponseEntity.ok(savedCategories);
    }
	
	@GetMapping("/all")
//	@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
	public List<Category> listAllCategory(){
		return service.listAllCategory();
	}
	@GetMapping("/id/{category_id}")
	public Category findById(@PathVariable(value = "category_id") Integer categoryId) {
		return service.findByCategoryId(categoryId);
	}
	
	@GetMapping("/{category_name}")
	public Category findByName(@PathVariable(value = "category_name") String name) {
		return service.findByCategoryName(name);
	}
	
}
