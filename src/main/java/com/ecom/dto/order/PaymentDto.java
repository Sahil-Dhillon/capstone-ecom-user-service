package com.ecom.dto.order;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
	private int paymentId;
	private String paymentMethod;
	private double totalAmount;
	private LocalDateTime createdAt;
	private String status;

}
