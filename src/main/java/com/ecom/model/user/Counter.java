package com.ecom.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Counter {
    @Id
    private int id = 1; // Singleton: Always one record
    private int categoryCount=0;
    private int productCount=0;
    private int userCount=0;
    private int orderCount=0;
    
    public Counter() {
    	
    }

    public Counter(int categoryCount, int productCount, int userCount, int orderCount) {
		super();
		this.categoryCount = categoryCount;
		this.productCount = productCount;
		this.userCount = userCount;
		this.orderCount = orderCount;
	}
	// Getters and Setters
    
    
}

