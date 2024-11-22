package com.ecom.controller.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.model.inventory.Products;
import com.ecom.service.inventory.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
    private ProductService service;
	
	@PostMapping("/add/{subCategory_id}")
	public Products add(@PathVariable(value = "subCategory_id") Integer subCategoryId,@RequestBody Products product) {
		
		return service.addProduct(subCategoryId,product);
	}
	
	@GetMapping
	public List<Products> listAllProducts(
			@RequestParam(value="pageNumber" ,defaultValue="0",required=false)Integer pageNumber,
			@RequestParam(value="pageSize" ,defaultValue="5",required=false)Integer pageSize,
			@RequestParam(value="sortBy" ,defaultValue="productId",required=false)String sortBy,
			@RequestParam(value="sort" ,defaultValue="asc",required=false)String sortDir
			){
		return service.listAllProducts(pageNumber,pageSize,sortBy,sortDir);
	}
	
	@GetMapping("/{product_id}")
	public Products listAllProductsByVendorId(@PathVariable(value = "product_id") Integer productId){
		return service.listAllById(productId);
	}
	
	@GetMapping("/vendorId/{vendor_id}")
	public List<Products> listAllProductsByVendorId(@PathVariable(value = "vendor_id") String VendorId){
		return service.listAllByVendorId(VendorId);
	}
	
	
	@GetMapping("/brand/{brand}")
	public List<Products> listAllProductsByBrand(@PathVariable(value = "brand") String brand){
		return service.listAllByBrand(brand);
	}
	
	@GetMapping("/price/{price1}/{price2}")
	public List<Products> listAllProductsByBrand(@PathVariable(value = "price1") Integer price1,@PathVariable(value = "price2") Integer price2){
		return service.listAllByPriceBetween(price1, price2);
	}
	
	@GetMapping("/{tags}")
	public List<Products> searchBar(@PathVariable(value = "tags") String tags){
		return service.searchByTag(tags);
	}
	
	
}
