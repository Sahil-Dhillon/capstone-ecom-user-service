package com.ecom.service.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecom.dao.inventory.ICategoryRepo;
import com.ecom.dao.inventory.IProductRepo;
import com.ecom.dao.inventory.IReviewRepo;
import com.ecom.dao.inventory.ISubcategoryRepo;
import com.ecom.model.inventory.Products;
import com.ecom.model.inventory.Review;

@Service
public class ReviewService {
	@Autowired
    private IReviewRepo reviewRepo;
	
	@Autowired
    private IProductRepo productRepo;
	
	public Review addReview(Review review, int productId) {
		
		Products product= productRepo.findById(productId).get();
		
		product.setProductRating(productId, review.getRating());
				
		List<Review> listCheck=product.getListOfReviews();
		
		listCheck.add(review);
				
		product.setListOfReviews(listCheck);
		
		
	
		productRepo.saveAndFlush(product);
		 
		return review;
	}
		
}
