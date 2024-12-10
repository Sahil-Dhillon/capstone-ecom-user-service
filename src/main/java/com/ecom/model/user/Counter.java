package com.ecom.model.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecom.dao.inventory.ICategoryRepo;
import com.ecom.dao.inventory.IProductRepo;
import com.ecom.dao.order.IOrderRepo;
import com.ecom.dao.user.IUserRepo;
import com.ecom.service.inventory.CategoryService;
import com.ecom.service.inventory.ProductService;
import com.ecom.service.order.OrderService;
import com.ecom.service.user.UserService;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
public class Counter {
    
    private long categoryCount;
    private long productCount;
    private long userCount;
    private long orderCount;
    
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

