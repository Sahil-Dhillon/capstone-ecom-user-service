package com.ecom.service.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.ecom.dao.cart.IWishlistItemRepo;
import com.ecom.dao.order.IOrderItemRepo;
import com.ecom.dao.order.IOrderRepo;
import com.ecom.dao.order.IPaymentRepo;
import com.ecom.dao.user.IUserRepo;
import com.ecom.model.cart.WishlistItem;
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
	private IPaymentRepo paymentRepository;
 
	// userId,coupon,orderStatus,totalAmount
	public Order placeOrder(String userId,String coupon,String orderStatus,double totalAmount, List<OrderItem> listOfOrderItems,Payment payment) {
		UserDetails user=userRepository.getById(userId);
		Order placedOrder=user.getOrder();
		//Order placedOrder=new Order();
		placedOrder.setCoupon(coupon);
		placedOrder.setOrderStatus(orderStatus);
		placedOrder.setTotalAmount(totalAmount);
		placedOrder.setListOfOrderItems(listOfOrderItems);
		Payment paymentDetail=new Payment();
		paymentDetail.setCreatedAt(LocalDateTime.now());
		paymentDetail.setPaymentMethod(payment.getPaymentMethod());
		paymentDetail.setStatus(payment.getStatus());
		paymentDetail.setTotalAmount(payment.getTotalAmount());
		placedOrder.setPayment(paymentDetail);
		//user.setOrder(placedOrder);
		userRepository.saveAndFlush(user);
		return placedOrder;
		//paymentRepository.saveAndFlush(paymentDetail);
		//return orderRepository.saveAndFlush(placedOrder);
 
	}
	public Order updateOrder(Order order,String userId) {
		UserDetails user=userRepository.getById(userId);
		user.setOrder(order);
		userRepository.saveAndFlush(user);
	    return order;
	}
 
}

