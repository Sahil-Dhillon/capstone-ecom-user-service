package com.ecom.dto.order;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	private int orderId;
	private String txnToken;
	private String orderStatus;
	private double totalAmount;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String coupon; // coupon code
	private int addressId;
	private PaymentDto payment; // Simplified Payment details
	private List<OrderItemDto> listOfOrderItems;
}
