package com.ecom.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@AllArgsConstructor
@Data
@NoArgsConstructor
public class WishlistItemDto {
    private int wishlistItemId;
    private int productId;
}
