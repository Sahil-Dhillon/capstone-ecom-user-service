package com.ecom.service.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.cart.IWishlistItemRepo;
import com.ecom.dao.inventory.IProductRepo;
import com.ecom.dao.order.IOrderItemRepo;
import com.ecom.dao.order.IOrderRepo;
import com.ecom.dao.order.IPaymentRepo;
import com.ecom.dao.user.IUserRepo;
import com.ecom.dto.notification.EmailClient;
import com.ecom.dto.notification.EmailRequest;
import com.ecom.dto.order.OrderDto;
import com.ecom.dto.order.OrderItemDto;
import com.ecom.dto.order.PaymentDto;
import com.ecom.model.cart.WishlistItem;
import com.ecom.model.inventory.Products;
import com.ecom.model.order.Order;
import com.ecom.model.order.OrderItem;
import com.ecom.model.order.Payment;
import com.ecom.model.user.UserDetails;

@Service
public class OrderService {
	@Autowired
	private IOrderRepo orderRepository;

	@Autowired
	private IUserRepo userRepository;

	@Autowired
	private IProductRepo productRepository;

	@Autowired
	private IPaymentRepo paymentRepository;

	@Autowired
	private EmailClient emailClient;

	// userId,coupon,orderStatus,totalAmount
	public OrderDto placeOrder(String userId, String coupon, int addressId, String orderStatus, double totalAmount,
			List<OrderItem> listOfOrderItems, Payment payment) {
		UserDetails user = userRepository.findById(userId).get();
		System.out.println(userId + " is the user Id ");
		List<Order> orderList = user.getOrderList();
		System.out.println("Order List is " + orderList);
		Order placedOrder = new Order();
		placedOrder.setCoupon(coupon);
		// placedOrder.setOrderStatus(orderStatus);
		placedOrder.setAddressId(addressId);
		placedOrder.setTotalAmount(totalAmount);
		placedOrder.setCreatedAt(LocalDateTime.now());
		placedOrder.setListOfOrderItems(listOfOrderItems);
		Payment paymentDetail = new Payment();
		paymentDetail.setCreatedAt(LocalDateTime.now());
		paymentDetail.setPaymentMethod(payment.getPaymentMethod());
		paymentDetail.setStatus(payment.getStatus());
		paymentDetail.setTotalAmount(payment.getTotalAmount());
		placedOrder.setPayment(paymentDetail);
		

		if (payment.getPaymentMethod().equals("wallet")) {
			if(user.getWalletBalance() > totalAmount) {
				System.out.println("Inside wallet check 1");
				if (!updateQty(listOfOrderItems).isEmpty()) {
					System.out.println("Inside wallet check1 Failed");
					placedOrder.setOrderStatus("Invalid Quantities");
				} else {
					System.out.println("Inside wallet check 1 Successful");
					placedOrder.setOrderStatus("Successful");
					user.setWalletBalance(user.getWalletBalance() - totalAmount);
				}
			}else {
				System.out.println("Inside wallet check1 Failed");
				placedOrder.setOrderStatus("Insufficient Balance");
			}
			
		} else{
			System.out.println("Inside other payment check ");
			if (!updateQty(listOfOrderItems).isEmpty()) {
				System.out.println("Inside other payment check failed");
				placedOrder.setOrderStatus("Invalid Quantities");
			} else {
				System.out.println("Inside other payment check success");
				placedOrder.setOrderStatus("Successful");
			}
		}
		user.getOrderList().add(placedOrder);

		userRepository.saveAndFlush(user);
		int size = user.getOrderList().size();
		Order po = user.getOrderList().get(size - 1);
		String recipientEmail = user.getEmail();
		CompletableFuture.runAsync(() -> 
		processOrder(po, recipientEmail)
		);
		return mapToOrderDTO(placedOrder);
		// paymentRepository.saveAndFlush(paymentDetail);
		// return orderRepository.saveAndFlush(placedOrder);

	}

	private String updateQty(List<OrderItem> orderItems) {
		StringBuilder outOfStockProducts = new StringBuilder();

		// First pass: Check stock availability for all products
		for (OrderItem orderItem : orderItems) {
			Integer productId = orderItem.getProductId();
			int orderedQty = orderItem.getQuantity();

			// Fetch product from repository
			Products product = productRepository.findByProductId(productId);
			if (product == null) {
				throw new RuntimeException("Product not found: " + productId);
			}

			// Check stock
			if (product.getQuantity() < orderedQty) {
				outOfStockProducts.append(product.getName()).append(" (required: ").append(orderedQty)
						.append(", available: ").append(product.getQuantity()).append("), ");
			}
		}

		// If any product is out of stock, return the failure message
		if (outOfStockProducts.length() > 0) {
			return outOfStockProducts.substring(0, outOfStockProducts.length() - 2); // Remove trailing ", "
		}

		// Second pass: Deduct stock for all products (only if all are available)
		for (OrderItem orderItem : orderItems) {
			Integer productId = orderItem.getProductId();
			int orderedQty = orderItem.getQuantity();

			Products product = productRepository.findByProductId(productId);
			if (product != null) {
				product.setQuantity(product.getQuantity() - orderedQty);
				productRepository.save(product);
			}
		}
		// Return empty string if stock updates were successful
		return "";
	}

	public OrderDto updateOrder(Order order, String userId) {
		UserDetails user = userRepository.findById(userId).get();
		user.getOrderList().add(order);

		userRepository.saveAndFlush(user);

		return mapToOrderDTO(order);
	}

	public OrderDto mapToOrderDTO(Order order) {
		OrderDto orderDTO = new OrderDto();
		orderDTO.setOrderId(order.getOrderId());
		orderDTO.setOrderStatus(order.getOrderStatus());
		orderDTO.setTotalAmount(order.getTotalAmount());
		orderDTO.setCreatedAt(order.getCreatedAt());
		orderDTO.setUpdatedAt(order.getUpdatedAt());
		orderDTO.setCoupon(order.getCoupon());
		orderDTO.setAddressId(order.getAddressId());
		// Map Payment to PaymentDTO
		PaymentDto paymentDTO = new PaymentDto();
		Payment payment = order.getPayment();
		paymentDTO.setPaymentId(payment.getPaymentId());
		paymentDTO.setPaymentMethod(payment.getPaymentMethod());
		paymentDTO.setTotalAmount(payment.getTotalAmount());
		paymentDTO.setCreatedAt(payment.getCreatedAt());
		paymentDTO.setStatus(payment.getStatus());
		orderDTO.setPayment(paymentDTO);

		// Map List<OrderItem> to List<OrderItemDTO>
		List<OrderItemDto> itemDTOs = order.getListOfOrderItems().stream().map(item -> {
			OrderItemDto itemDTO = new OrderItemDto();
			itemDTO.setOrderItemId(item.getOrderItemId());
			itemDTO.setProductId(item.getProductId());
			itemDTO.setPrice(item.getPrice());
			itemDTO.setQuantity(item.getQuantity());
			itemDTO.setVariations(item.getVariations());
			itemDTO.setVendorId(item.getVendorId());
			return itemDTO;
		}).collect(Collectors.toList());

		orderDTO.setListOfOrderItems(itemDTOs);
		return orderDTO;
	}

	public String processOrder(Order orderRequest, String recipientEmail) {
		Products product = null;
		String items = orderRequest.getListOfOrderItems().stream()
				.map(item -> "Product: " + productRepository.findById(item.getProductId()).get().getName()
						+ ", Price: $" + item.getPrice() + ", Variations: " + item.getVariations() + ", Quantity: "
						+ item.getQuantity())
				.collect(Collectors.joining("\n"));

		// Create email content for order notification
		EmailRequest emailRequest = new EmailRequest(recipientEmail,
				"Dear Customer,\n\nYour order has been successfully placed.\nOrder ID: " + orderRequest.getOrderId()
						+ "\nItems: " + items + "\n\nThank you for shopping with us!",
				"Order Confirmation - " + orderRequest.getOrderId());

		// Send email notification
		return emailClient.sendEmail(emailRequest);
	}

}
