package com.ecom.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private int cartItemId;
    private int productId;
    private int quantity;
    private String variations;
 
   
}