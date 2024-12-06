package com.ecom.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.ecom.dto.order.OrderDto;
import com.ecom.model.order.Order;
import com.ecom.model.order.OrderItem;
import com.ecom.model.order.Payment;
import com.ecom.model.user.UserDetails;
import com.ecom.service.cart.CartItemService;
import com.ecom.service.order.OrderService;
import com.ecom.service.user.UserService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
    private OrderService orderService;
	
	private final UserService userService;

    public OrderController(UserService userService) {
        this.userService = userService;
    }
	
	//userId,String coupon,String orderStatus,double totalAmount, List<OrderItem> listOfOrderItems,Payment payment
	
    @PostMapping("/placeorder")
    public ResponseEntity<OrderDto> addItemToCart(
            @RequestBody Order order) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        UserDetails currentUser = (UserDetails) authentication.getPrincipal();
        UserDetails user = userService.findByUsername(currentUser.getUsername());
        OrderDto orderPlaced = orderService.placeOrder(user.getUserId(), order.getCoupon(),order.getAddressId(), order.getOrderStatus(), order.getTotalAmount(), order.getListOfOrderItems(), order.getPayment());
        return ResponseEntity.ok(orderPlaced);
    }
    
   
    @PutMapping("/updateorder")
    public ResponseEntity<OrderDto> updateOrder(
            @RequestParam String userId,
            @RequestBody Order order) {
        OrderDto orderUpdated = orderService.updateOrder(order,userId);
        return ResponseEntity.ok(orderUpdated);
    }

   
}
