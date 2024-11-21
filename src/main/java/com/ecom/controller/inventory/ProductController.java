package com.ecom.controller.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.model.inventory.Products;
import com.ecom.service.inventory.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
    private ProductService service;
	
	@PostMapping("/add/{subCategory_id}")
	public Products add(@PathVariable(value = "subCategory_id") String subCategoryId,@RequestBody Products product) {
		
		return service.addProduct(subCategoryId,product);
	}
	
	@GetMapping
	public List<Products> listAllProducts(){
		return service.listAllProducts();
	}
	
}
