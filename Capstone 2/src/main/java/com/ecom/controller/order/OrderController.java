package com.ecom.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.cart.CartDto;
import com.ecom.dto.cart.CartItemDto;
import com.ecom.model.order.Order;
import com.ecom.model.order.OrderItem;
import com.ecom.model.order.Payment;
import com.ecom.service.cart.CartItemService;
import com.ecom.service.order.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	// userId,String coupon,String orderStatus,double totalAmount, List<OrderItem>
	// listOfOrderItems,Payment payment
	@PostMapping("/placeorder")
	public ResponseEntity<Order> placeOrder(@RequestParam String userId, @RequestBody Order order) {
		Order orderPlaced = orderService.placeOrder(userId, order.getCoupon(), order.getOrderStatus(),
				order.getTotalAmount(), order.getListOfOrderItems(), order.getPayment());
		return ResponseEntity.ok(orderPlaced);
	}

	@PutMapping("/updateorder")
	public ResponseEntity<Order> updateOrder(@RequestParam String userId, @RequestBody Order order) {
		Order orderUpdated = orderService.updateOrder(order, userId);
		return ResponseEntity.ok(orderUpdated);
	}

}
