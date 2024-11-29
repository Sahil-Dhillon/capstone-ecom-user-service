package com.ecom.service.inventory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecom.dao.inventory.ICategoryRepo;
import com.ecom.model.inventory.Category;
import com.ecom.service.user.CounterService;


@Service
public final class CategoryService {
	
	@Autowired
	private ICategoryRepo categoryRepository;
	
	@Autowired
	private CounterService counterService;
	
	public Category addCategory(Category category) {
		counterService.incrementCategoryCount();
		return categoryRepository.saveAndFlush(category);
		
	}
	
	public List<Category> listAllCategory(){
		return categoryRepository.findAll();
	}
	
	public Category findByCategoryId(Integer id) {
		return categoryRepository.findByCategoryId(id);
	}
	
	public Category findByCategoryName(String name) {
		return categoryRepository.findByName(name);
	}
	
	public List<Category> saveAllCategories(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }
	

	
}
