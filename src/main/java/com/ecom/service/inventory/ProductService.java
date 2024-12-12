package com.ecom.service.inventory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecom.client.NotificationServiceClient;
import com.ecom.dao.inventory.ICategoryRepo;
import com.ecom.dao.inventory.IProductRepo;
import com.ecom.dao.inventory.ISubcategoryRepo;
import com.ecom.dao.user.IUserRepo;
import com.ecom.dto.notification.EmailRequest;
import com.ecom.model.inventory.Category;
import com.ecom.model.inventory.Products;
import com.ecom.model.inventory.Subcategory;

@Service
public final class ProductService {
	@Autowired
	private IProductRepo productRepo;
	@Autowired
	private ISubcategoryRepo subcategoryRepository;
	@Autowired
	private ICategoryRepo categoryRepository;
	@Autowired
	private NotificationServiceClient notificationServiceClient;
	@Autowired
	private IUserRepo userRepository;

	public Products addProduct(Integer subCategoryId, Products product) {
		if (product != null) {

			Subcategory subCategory = subcategoryRepository.findById(subCategoryId)
					.orElseThrow(() -> new IllegalArgumentException("Invalid subcategory ID"));

			product.setCategory(subCategory.getCategory());
			product.setStatus("pending");
			product.setAvailable(false);
			product.setSubCategory(subCategory);

			productRepo.saveAndFlush(product);
			return product;
		}
		// Notify Admin about Product Request

		String adminEmail = userRepository.findAll().stream().filter(item -> item.getRoles().equals("ADMIN"))
				.findFirst().get().getEmail();
		String vendorId = product.getVendorId();
		String vendorEmail = userRepository.findById(vendorId).get().getEmail();
		EmailRequest emailRequest = new EmailRequest(adminEmail,
				"Vendor " + vendorEmail + " has requested to add the product:\n\n" + product.toString(),
				"Product Request from Vendor");
		notificationServiceClient.sendEmail(emailRequest);

		System.out.println("Failed to add product");
		return null;

	}

	public List<Products> listAllProducts(int pageNumber, int pageSize, String sortBy, String sortDir) {
		Sort sort = null;
		if (sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p = (Pageable) PageRequest.of(pageNumber, pageSize, sort);
		Page<Products> pageContents = productRepo.findAll(p);
		List<Products> listofContentOnOnePage = pageContents.getContent().stream().filter(item->item.getStatus().equalsIgnoreCase("accepted")).collect(Collectors.toList());;
		return listofContentOnOnePage;

	}

	public List<Products> listAllByVendorId(String vendorId) {
		return productRepo.findByVendorId(vendorId);
	}

	public List<Products> listAllAddRequests() {
		return productRepo.findByStatus("pending");
	}

	public Products listById(Integer id) {
		return productRepo.findByProductId(id);
	}

	public List<Products> listAllByBrand(String brand) {
		return productRepo.findByBrand(brand).stream().filter(item->item.getStatus().equalsIgnoreCase("accepted")).collect(Collectors.toList());
	}

	public List<Products> listAllByPriceBetween(Integer lowerLimit, Integer upperLimit) {
		return productRepo.findByPriceBetween(lowerLimit, upperLimit).stream().filter(item->item.getStatus().equalsIgnoreCase("accepted")).collect(Collectors.toList());
	}

	public List<Products> listAllProductsBySubcategoryId(int pageNumber, int pageSize, String sortBy, String sortDir,
			Subcategory subcategory) {
		Sort sort = null;
		if (sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p = (Pageable) PageRequest.of(pageNumber, pageSize, sort);
		Page<Products> pageContents = productRepo.findBySubCategory(subcategory, p);
		List<Products> listofContentOnOnePage = pageContents.getContent().stream().filter(item->item.getStatus().equalsIgnoreCase("accepted")).collect(Collectors.toList());
		return listofContentOnOnePage;
	}

	public List<Products> searchByTagUnderSubcategory(int pageNumber, int pageSize, String sortBy, String sortDir,
			String tags, int subcategoryId) {
		Sort sort = null;
		if (sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p = (Pageable) PageRequest.of(pageNumber, pageSize, sort);
		List<Products> pageContents = productRepo.findByTagsContaining(tags, p).stream()
				.filter(item -> item.getSubCategory().getSubcategoryId() == subcategoryId && item.getStatus().equalsIgnoreCase("accepted")).collect(Collectors.toList());
		return pageContents;
	}

	public List<Products> searchByTag(int pageNumber, int pageSize, String sortBy, String sortDir, String tags) {
		Sort sort = null;
		if (sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p = (Pageable) PageRequest.of(pageNumber, pageSize, sort);
		List<Products> pageContents = productRepo.findByTagsContaining(tags, p).stream().filter(item->item.getStatus().equalsIgnoreCase("accepted")).collect(Collectors.toList());
		return pageContents;
	}

	public Products approveProduct(int productId) {
		Products product = productRepo.findByProductId(productId);
		product.setStatus("Accepted");
		CompletableFuture.runAsync(() -> notifyBoth(productId, "Accepted"));
		return productRepo.saveAndFlush(product);
	}

	public Products rejectProduct(int productId) {
		Products product = productRepo.findByProductId(productId);
		product.setStatus("Rejected");
		CompletableFuture.runAsync(() -> notifyBoth(productId, "Rejected"));
		return productRepo.saveAndFlush(product);
	}

	private void notifyBoth(int productId, String status) {
		// Notify Admin about Product Request
		Products product = productRepo.findByProductId(productId);

	    String adminEmail = "ashtapalak@gmail.com";
		String vendorId = product.getVendorId();
					String vendorEmail = vendorId;
																								// address
					String decisionMessage = "The product " + product.getName() + " has been " + status.toUpperCase()+".";
		// Notify Vendor
		EmailRequest vendorEmailRequest = new EmailRequest(vendorEmail, decisionMessage,
		"Product Request " + status.toUpperCase());
		notificationServiceClient.sendEmail(vendorEmailRequest);

					// Notify Admin
		EmailRequest adminEmailRequest = new EmailRequest(adminEmail, "The product " + product.getName()
		+ " from vendor " + vendorEmail + " has been " + status.toUpperCase() + ".",
		"Product Decision Made - " + status.toUpperCase());
		notificationServiceClient.sendEmail(adminEmailRequest);
	}

	public Products editProduct(int productId,  int quantity) {
		Products product = productRepo.findByProductId(productId);
		if (product.getStatus().equals("Accepted")) {
			product.setQuantity(quantity);
			return productRepo.saveAndFlush(product);
		} else if (product.getStatus().equals("pending"))
			return null;
		else
			return null;
//		return productRepo.saveAndFlush(product);
	}
  
    public List<Products> util(int pageNumber, int pageSize, String sortBy, String sortDir, String[] tags) {
		// Fetch all products with pagination and sorting
		System.out.println("in util");
		Pageable p = PageRequest.of(pageNumber, pageSize,
				sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		List<Products> products = productRepo.findByTagsContaining(tags[0], p);
		System.out.println("Fisrt tag " + tags[0]);
		System.out.println("Inside Util---" + products);
		// Filter products by tags iteratively
		System.out.println("String " + tags);
		for (String tag : tags) {
			System.out.println("Processing tag: " + tag);
 
			products = products.stream().filter(product -> {
				// Split the product's tags into individual words
				String[] productTags = product.getTags() != null ? product.getTags().split(" ") : new String[0];
				System.out.println("TAG " + productTags);
				// Check if the current tag exists in the product's tags
				return Arrays.stream(productTags).anyMatch(productTag -> productTag.contains(tag));
			}).filter(item->item.getStatus().equalsIgnoreCase("accepted")).collect(Collectors.toList());
		}
 
		return products;
	}
 
	public List<Products> util2(int pageNumber, int pageSize, String sortBy, String sortDir, String[] tags,Subcategory subcategory) {
		// Fetch all products with pagination and sorting
		System.out.println("in util");
		Pageable p = PageRequest.of(pageNumber, pageSize,
				sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		Page<Products> pageContents = productRepo.findBySubCategory(subcategory, p);
		List<Products> products = pageContents.getContent();
		System.out.println("Fisrt tag " + tags[0]);
		System.out.println("Inside Util---" + products);
		// Filter products by tags iteratively
		System.out.println("String " + tags);
		for (String tag : tags) {
			System.out.println("Processing tag: " + tag);
 
			products = products.stream().filter(product -> {
				// Split the product's tags into individual words
				String[] productTags = product.getTags() != null ? product.getTags().split(" ") : new String[0];
				System.out.println("TAG " + productTags);
				// Check if the current tag exists in the product's tags
				return Arrays.asList(productTags).contains(tag);
			}).filter(item->item.getStatus().equalsIgnoreCase("accepted")).collect(Collectors.toList());
		}
 
		return products;
	}
}
