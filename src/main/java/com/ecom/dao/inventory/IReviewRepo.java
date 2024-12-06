package com.ecom.dao.inventory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.inventory.Review;

public interface IReviewRepo extends JpaRepository<Review, Integer>{

	

}
