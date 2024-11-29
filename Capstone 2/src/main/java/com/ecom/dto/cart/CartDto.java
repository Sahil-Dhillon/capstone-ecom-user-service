package com.ecom.dto.cart;



import java.util.List;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private int cartId;
    private List<CartItemDto> cartItems;
 
    
}