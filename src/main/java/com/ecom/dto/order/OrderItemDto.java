package com.ecom.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
	private int orderItemId;
	private String productId;
	private int price;
	private int quantity;
	private String variations;
	private int vendorId;

}
