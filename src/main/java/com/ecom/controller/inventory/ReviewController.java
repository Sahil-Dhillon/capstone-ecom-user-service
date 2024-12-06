package com.ecom.controller.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.model.inventory.Review;
import com.ecom.service.inventory.ReviewService;
import com.ecom.service.inventory.SubcategoryService;

@RestController
@RequestMapping("/review")
public class ReviewController {
	@Autowired
    private ReviewService service;
	
	
	@PostMapping("/add/{product_id}")
	public Review addReview(@RequestBody Review review,@PathVariable(value = "product_id") Integer productId) {
		
		
		return service.addReview(review,productId);
	}

	
}
