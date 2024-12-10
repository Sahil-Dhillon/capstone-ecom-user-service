package com.ecom.controller.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.model.inventory.Products;
import com.ecom.model.inventory.Subcategory;
import com.ecom.service.inventory.ProductService;
import com.ecom.service.inventory.SubcategoryService;



@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
    private ProductService service;
	
	@Autowired
	private SubcategoryService subcategoryservice;
	
	
//	@GetMapping("/admin")
//  @PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/add/{subCategory_id}")
	public Products add(@PathVariable(value = "subCategory_id") Integer subCategoryId,@RequestBody Products product) {
		product.setAvailable(true);
		return service.addProduct(subCategoryId,product);
	}
	
	@GetMapping
//	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Products> listAllProducts(
			@RequestParam(value="pageNumber" ,defaultValue="0",required=false)Integer pageNumber,
			@RequestParam(value="pageSize" ,defaultValue="5",required=false)Integer pageSize,
			@RequestParam(value="sortBy" ,defaultValue="productId",required=false)String sortBy,
			@RequestParam(value="sort" ,defaultValue="asc",required=false)String sortDir
			){
		return service.listAllProducts(pageNumber,pageSize,sortBy,sortDir);
	}
	
	@GetMapping("/{product_id}")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public Products listProductById(@PathVariable(value = "product_id") Integer productId){
		return service.listById(productId);
	}
	
	@GetMapping("/vendorId/{vendor_id}")
	public List<Products> listAllProductsByVendorId(@PathVariable(value = "vendor_id") String VendorId){
		return service.listAllByVendorId(VendorId);
	}
	
	@GetMapping("/requests")
	public List<Products> listAllProductAddRequests(){
		return service.listAllAddRequests();
	}
	
	
	@GetMapping("/brand/{brand}")
	public List<Products> listAllProductsByBrand(@PathVariable(value = "brand") String brand){
		return service.listAllByBrand(brand);
	}
	
	@GetMapping("/price/{price1}/{price2}")
	public List<Products> filterByPrice(@PathVariable(value = "price1") Integer price1,@PathVariable(value = "price2") Integer price2){
		return service.listAllByPriceBetween(price1, price2);
	}
	
	@GetMapping("/search/{tags}/{subcategoryid}")
	public List<Products> listAllProductsBySubCategory(@PathVariable(value = "tags") String tags,
			@RequestParam(value="pageNumber" ,defaultValue="0",required=false)Integer pageNumber,
			@RequestParam(value="pageSize" ,defaultValue="1",required=false)Integer pageSize,
			@RequestParam(value="sortBy" ,defaultValue="price",required=false)String sortBy,
			@RequestParam(value="sort" ,defaultValue="asc",required=false)String sortDir,
			@PathVariable(value = "sucategoryid") int subcategoryId
			){
		return  service.searchByTagUnderSubcategory(pageNumber, pageSize, sortBy, sortDir, tags,subcategoryId);
	}

	@GetMapping("/search/{tags}")
	public List<Products> searchByTags(@PathVariable(value = "tags") String tags,
			@RequestParam(value="pageNumber" ,defaultValue="0",required=false)Integer pageNumber,
			@RequestParam(value="pageSize" ,defaultValue="1",required=false)Integer pageSize,
			@RequestParam(value="sortBy" ,defaultValue="price",required=false)String sortBy,
			@RequestParam(value="sort" ,defaultValue="asc",required=false)String sortDir
			){
		return  service.searchByTag(pageNumber, pageSize, sortBy, sortDir, tags);
	}
	
	@GetMapping("/bySubCategory/{subcategory_id}")
	public List<Products> listAllProductsBySubCategory(@PathVariable(value = "subcategory_id") Integer subCategoryId,
			@RequestParam(value="pageNumber" ,defaultValue="0",required=false)Integer pageNumber,
			@RequestParam(value="pageSize" ,defaultValue="1",required=false)Integer pageSize,
			@RequestParam(value="sortBy" ,defaultValue="price",required=false)String sortBy,
			@RequestParam(value="sort" ,defaultValue="asc",required=false)String sortDir
			){
		Subcategory subcategory=subcategoryservice.findBySubcategoryId(subCategoryId);
		return service.listAllProductsBySubcategoryId(pageNumber,pageSize,sortBy,sortDir,subcategory);
	}
	
	@PutMapping("approve/{product_id}")
	public Products approveProduct(@PathVariable(value="product_id") Integer pid) {
		return service.approveProduct(pid);
	}
	
	@PutMapping("reject/{product_id}")
	public Products rejectProduct(@PathVariable(value="product_id") Integer pid) {
		return service.rejectProduct(pid);
	}
}
